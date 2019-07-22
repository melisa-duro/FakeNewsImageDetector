package manager;

import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Tweet;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
* 
 * @author melisa_duro
 */

public class TweetManager {

    private static final HttpClient defaultHttpClient = HttpClients.createDefault();

    public static HashMap<String, Integer> contHT = new HashMap<String, Integer>();

    static {
        Logger.getLogger("org.apache.http").setLevel(Level.OFF);
    }

    /**
     * @param username A specific username (without @)
     * @param since Lower bound date (yyyy-mm-dd)
     * @param until Upper bound date (yyyy-mm-dd)
     * @param scrollCursor (Parameter used by Twitter to do pagination of
     * results)
     * @return JSON response used by Twitter to build its results
     * @throws Exception
     */
    private static String getURLResponse(String username, String since, String until, String querySearch, String scrollCursor, String type) throws Exception {
        String appendQuery = "";
        if (username != null) {
            appendQuery += " from:" + username;
        }
        if (since != null) {
            appendQuery += " since:" + since;
        }
        if (until != null) {
            appendQuery += " until:" + until;
        }
        if (querySearch != null) {
            appendQuery += " " + querySearch;
        }

        //Coletilla URL para filtrar por tipo de url de búsqueda (por defecto siempre utilizará el criterio de Timeline)
        String coletilla = "";
        if (type != null) {

            if ("Destacados".equals(type)) {
                coletilla = "";
            }
            if ("Timeline".equals(type)) {
                coletilla = "f=tweets";
            }
            if (!"Noticias".equals(type)) {
            } else {
                coletilla = "f=news";
            }

        }

        //"near:spain
        String url = String.format("https://twitter.com/i/search/timeline?" + coletilla + "&q=%s&src=typd&max_position=%s", URLEncoder.encode(appendQuery, "UTF-8"), scrollCursor);
        //String url = String.format("https://twitter.com/i/search/timeline?f=tweets&q=%s&src=typd&max_position=%s", URLEncoder.encode(appendQuery, "UTF-8"), scrollCursor);

        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        HttpEntity resp = defaultHttpClient.execute(httpGet).getEntity();

        return EntityUtils.toString(resp);
    }

    /**
     * @param criteria An object of the class {@link TwitterCriteria} to
     * indicate how tweets must be searched
     *
     * @return A list of all tweets found
     */
    public static List<Tweet> getTweets(TwitterCriteria criteria) {
        List<Tweet> results = new ArrayList<Tweet>();
        int i = 1;
        
        try {

            String refreshCursor = null;
            outerLace:
            while (true) {
                JSONObject json = new JSONObject(getURLResponse(criteria.getUsername(), criteria.getSince(), criteria.getUntil(), criteria.getQuerySearch(), refreshCursor, criteria.getType()));
                refreshCursor = json.getString("min_position");
                Document doc = Jsoup.parse((String) json.get("items_html"));
                Elements tweets = doc.select("div.js-stream-tweet");
                //System.out.println(tweets);
                if (tweets.isEmpty()) {
                    break;
                }

                for (Element tweet : tweets) {

                    String usernameTweet = tweet.attr("data-screen-name");
                    String txt = tweet.select("p.js-tweet-text").text().replaceAll("[^\\u0000-\\uFFFF]", "");
                    int retweets = Integer.valueOf(tweet.select("span.ProfileTweet-action--retweet span.ProfileTweet-actionCount").attr("data-tweet-stat-count").replaceAll(",", ""));
                    int favorites = Integer.valueOf(tweet.select("span.ProfileTweet-action--favorite span.ProfileTweet-actionCount").attr("data-tweet-stat-count").replaceAll(",", ""));
                    long dateMs = Long.valueOf(tweet.select("small.time span.js-short-timestamp").attr("data-time-ms"));
                    String id = tweet.attr("data-tweet-id");
                    String permalink = tweet.attr("data-permalink-path");
                    String imageurl = tweet.select("div.js-adaptive-photo").attr("data-image-url");
                    Elements cuentaverified = tweet.select("div.stream-item-header").select("a.js-nav span.UserBadges span.Icon").select("span.u-hiddenVisually");
                    
                    
                    String cuenta_verificada = "";
                    if (cuentaverified.size() > 0) {
                        cuenta_verificada = "Cuenta verificada";
                    } else {
                        cuenta_verificada = "Cuenta no verificada";
                    }


                    String geo = "";
                    Elements geoElement = tweet.select("span.Tweet-geo");
                    if (geoElement.size() > 0) {
                        geo = geoElement.attr("title");
                    }

                    String lang = tweet.select("div.js-tweet-text-container p.tweet-text").attr("lang");
                    Date date = new Date(dateMs);
                    //System.out.println(tweet);

                    if (!"".equals(imageurl)) {
                        Tweet t = new Tweet();
                        t.setId(id);
                        t.setPermalink("https://twitter.com" + permalink);
                        t.setUsername(usernameTweet);
                        t.setText(txt);
                        t.setDate(date);
                        t.setRetweets(retweets);
                        t.setFavorites(favorites);
                        t.setMentions(processTerms("(@\\w*)", txt));
                        t.setHashtags(processTerms("(#\\w*)", txt));
                        t.setGeo(geo);
                        t.setLang(lang);
                        t.setImageURL(imageurl);
                        t.setVerified(cuenta_verificada);


                        results.add(t);

//                        System.out.println("Date: " + t.getDate());
//                        System.out.println("ImageURL: " + t.getImageURL());
//                        System.out.println(t.getText());
                    }

                    i++;
                }

            }

            //bw.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return results;
    }

    
    private static String processTerms(String patternS, String tweetText) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = Pattern.compile(patternS).matcher(limpiarAcentos(tweetText));

        while (matcher.find()) {
            sb.append(matcher.group());
            sb.append(" ");
        }

        if (sb.toString().trim().endsWith("PIC")) {
            String sub;
            sub = sb.toString().substring(0, sb.toString().length() - 4);
            return sub.trim();
        }
        if (sb.toString().trim().endsWith("HTTPS")) {
            String sub;
            sub = sb.toString().substring(0, sb.toString().length() - 6);
            return sub.trim();
        }
        if (sb.toString().trim().endsWith("HTTP")) {
            String sub;
            sub = sb.toString().substring(0, sb.toString().length() - 5);
            return sub.trim();
        } else {
            return sb.toString().trim();
        }
        //System.out.println(sub);

    }

    private static String limpiarAcentos(String cadena) {
        String limpio = null;
        if (cadena != null) {
            String valor = cadena;
            valor = valor.toUpperCase();
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
            limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }



}
