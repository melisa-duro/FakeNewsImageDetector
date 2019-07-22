/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fakenewsimagedetector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import manager.TweetManager;
import manager.TwitterCriteria;
import model.Tweet;
import java.net.URL;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    // Variable logger  
    public static final Logger logger = Logger.getLogger("SingleFileFormatterLog");

    // Variables de entrada
    public static String Date_ini = "";
    public static String Date_fin = "";
    public static ArrayList<String> allUsers = new ArrayList<String>();

    // API BING variables
    static String subscriptionKey = "36090f0befb64e8da88f08e74105f770";
    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v7.0/images/details?imgurl=";
    static String searchTerm = "";

    //Path init variables
    public static String PathLogs = "";
    public static String PathProject = "";
    public static String PathOutput = "";
    public static String strDate = "";

    public static void main(String[] args) throws IOException, URISyntaxException {
    }

    public static void start(String Date_ini, String Date_fin, ArrayList<String> allUsers) {

        // Inicializamos variables locales
        TwitterCriteria criteria = null;
        Tweet t = null;
        String fechaantigua = "";
        String fechareciente = "";
        String formatted = "";
        String formatted_tweet = "";

        // Inicializamos buffer donde escribiremos la salida
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(PathOutput + "\\Output_" + strDate + ".csv"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //Cabecera del fichero de salida
            bw.write("Cuenta;URL_imagen;URL_tweet;Cuenta_verificada;Fechaantigua_imagen;Fechareciente_imagen;Fecha_tweet;Diferencia_dia_antiguo;Diferencia_dia_reciente;Scoring");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


        /*Montamos los criterios de búsqueda*/
//        String Date_ini = "2019-07-01";
//        String Date_fin = "2019-07-02";
//        ArrayList<String> allUsers = new ArrayList<String>();
//        allUsers.add("el_pais");
//        allUsers.add("CNN");

        logger.logp(Level.INFO, "fakenewsimagedetector ->", "start_main", "INIT Twitter Search");

        //Concatenamos el array de cuentas de Twitter para montar la query de búsqueda
        String queryUser = "";
        for (int i = 0; i < allUsers.size(); i++) {
            String thisUser = allUsers.get(i);
            queryUser = queryUser + thisUser + " OR ";
        }
        queryUser = queryUser.substring(0, queryUser.length() - 4);
        //Cuenta-s donde buscamos los tweets
        System.out.println(queryUser);

        /*Extraemos los tweets de la pestaña de más recientes (se puede configurar)*/
        criteria = TwitterCriteria.create()
                .setUsername(queryUser)
                .setSince(Date_ini)
                .setUntil(Date_fin)
                .setType("Timeline");

        // Llamamos al método getTweets para extraer los campos de los tweets con imagenes
        List<Tweet> getTweets = TweetManager.getTweets(criteria);

        logger.logp(Level.INFO, "fakenewsimagedetector ->", "start_main", "INIT Bing Search");

        //A continuación recorremos todos los tweets que se han obtenido y los recorremos uno a uno, para realizar la búsqueda de las imagenes contenidas en ellos con la API de Bing Image Search
        for (int j = 0; j < getTweets.size(); j++) {

            if (subscriptionKey.length() != 32) {  // Comprobamos la API Key
                System.out.println("Invalid Bing Search API subscription key!");
                System.out.println("Please paste yours into the source code.");
                logger.logp(Level.WARNING, "fakenewsimagedetector ->", "start_reversesearch", "Invalid Bing Search API subscription key");
                System.exit(1);
            }

            try {

                t = getTweets.get(j); // Extraemos la información del tweet

                System.out.println("Buscando la imagen: " + t.getImageURL());  // Extraemos la url de la imagen

                // Llamamos al método SearchImages que nos devolverá un objeto result con la respuesta en formato JSON
                SearchResults result = SearchImages(t.getImageURL());
                

                //Parseamos el JSON
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(result.jsonResponse).getAsJsonObject();
                //System.out.println(json);
                
                fechaantigua = "";
                fechareciente = "";
                formatted = "";
                formatted_tweet = "";

                try {
                    //Extraemos la información de la respuesta devuelta (en formato JsonArray ya que tendremos varias imagenes de respuesta)
                    JsonArray jsonarray = (JsonArray) json.getAsJsonObject("visuallySimilarImages").getAsJsonArray("value");

                    for (int i = 0; i < jsonarray.size(); i++) {
                        //Extraemos la fecha de publicación para cada una de la imagenes devueltas
                        JsonObject jsonobject = (JsonObject) jsonarray.get(i);
                        //System.out.println(jsonobject);
                        JsonElement fecha = jsonobject.get("datePublished");
                        String fecha_string = fecha.getAsString();
                        //System.out.println(fecha_string);

                        // Parseamos la fecha en formato yyyy-MM-dd
                        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = input.parse(fecha_string);
                        formatted = output.format(d);
                        //System.out.println(formatted);
                        if (i == 0) {
                            fechaantigua = formatted;
                            fechareciente = formatted;
                        }

                        //Comparamos las fechas para quedarnos con la más antigua
                        if (formatted.compareTo(fechaantigua) < 0) {
                            fechaantigua = formatted;
                        }
                        //Comparamos las fechas para quedarnos con la más antigua
                        if (formatted.compareTo(fechareciente) > 0) {
                            fechareciente = formatted;
                        }

                    }

                    //Parseamos la fecha del tweer en el mismo formato yyyy-MM-dd
                    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                    formatted_tweet = output.format(t.getDate());

                    Date date_tweet = new SimpleDateFormat("yyyy-MM-dd").parse(formatted_tweet);
                    Date date_antiguo = new SimpleDateFormat("yyyy-MM-dd").parse(fechaantigua);
                    Date date_reciente = new SimpleDateFormat("yyyy-MM-dd").parse(fechareciente);

                    // Calculamos la diferencia entre la fecha del tweet y la fecha de publicación de la imagen
                    int dias = (int) ((date_tweet.getTime() - date_antiguo.getTime()) / 86400000);
                    int dias_reciente = (int) ((date_tweet.getTime() - date_reciente.getTime()) / 86400000);

                    //Creamos un scoring para detectar si la noticia es falsa o no
                    int Scoring = getScoringFakeNews(dias, dias_reciente, t.getRetweets(), t.getFavorites(), t.getVerified(), t.getDate(),date_reciente);
                    //System.out.println(Scoring);
                    
                    //Escribimos la salida
                    bw.newLine();
                    bw.write(String.format("Cuenta: %s; Imagen buscada: %s;URL del tweet: %s;Cuenta verificada: %s;"
                            + "La fecha mas antigua de la imagen es: %s;La fecha mas reciente de la imagen es: %s;"
                            + "La fecha del tweet es: %s;"
                            + "Hay %s dias de diferencia respecto a la fecha mas antigua;"
                            + "Hay %s dias de diferencia respecto a la fecha mas reciente;"
                            + "La imagen es falsa con un porcentaje de un %s %%",
                            t.getUsername(), t.getImageURL(), t.getPermalink(), t.getVerified(), fechaantigua, fechareciente, formatted_tweet, dias, dias_reciente,Scoring));

                    //System.out.println("Cuenta: " + t.getUsername());
                    //System.out.println("Imagen buscada:  " + t.getImageURL());
                    //System.out.println("URL del tweet: " + t.getPermalink());
                    //System.out.println("Cuenta verificada: " + t.getVerified());
                    //System.out.println("La fecha mas antigua de la imagen es: " + fechaantigua);
                    //System.out.println("La fecha del tweet es: " + formatted_tweet);
                    //System.out.println("Hay " + dias + " dias de diferencia\n");
                    
                } catch (Exception e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

                }
            } catch (Exception e) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                System.exit(1);
            }
        }
        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Done. Output file generated");

    }

    public static SearchResults SearchImages(String searchQuery) throws Exception {

        // Construye la URL para realizar la petición (endpoint + query string)
        URL url = new URL(host + path + URLEncoder.encode(searchQuery, "UTF-8") + "&modules=All");
        //System.out.println(url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // Cuerpo del JSON
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();

        // Construye el objeto results que será el que se devuelva
        SearchResults results = new SearchResults(new HashMap<String, String>(), response);

        // Extrae los encabezados HTTP relacionados con Bing
        Map<String, List<String>> headers = connection.getHeaderFields();
        for (String header : headers.keySet()) {
            if (header == null) {
                continue;
            }
            if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                results.relevantHeaders.put(header, headers.get(header).get(0));
            }
        }

        stream.close();
        connection.disconnect();
        return results;
    }

    private static int getScoringFakeNews(int dias, int dias_reciente, int retweets, int favorites, String verified, Date date, Date date_reciente) {

        int score = 0;

        // Si la diferencia con la fecha más antigua es de mas de un año, el score se incrementa en un 10%
        if (dias > 365) {
            score = score + 10;
        }

        // Si la diferencia con la fecha más reciente es de mas de un año, el score se incrementa en un 20%
        if (dias_reciente > 365) {
            score = score + 20;
        }

        // Si la suma de favoritos y retweets es menor que 100, el score se incrementará en un 20%
        if (retweets + favorites < 100) {
            score = score + 20;
        }

        // Si la cuenta no está verificada, el score se incrementará en un 20%
        if (!verified.equals("Cuenta verificada")) {
            score = score + 30;
        }


        // Si la fecha de publicación de la imagen (más reciete) es posterior a la fecha de publicación del tweet, el score se incrementará en un 30%
        if (date_reciente.compareTo(date) > 0) {
            score = score + 20;
        }

        return score;
    }

}
