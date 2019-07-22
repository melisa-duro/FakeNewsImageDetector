package model;

import java.util.Date;

/**
* 
 * @author melisa_duro
 */
public class Tweet {
	
	private String id;
	
	private String permalink;
	
	private String username;
	
	private String text;
	
	private Date date;
	
	private int retweets;

	private int favorites;
	
	private String mentions;
	
	private String hashtags;
	
	private String geo;
        
        private String lang;
        
        private String imageurl;
        
        private String verified;
	
	public Tweet() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRetweets() {
		return retweets;
	}

	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}
	
	public String getMentions() {
		return mentions;
	}
        
        public String getVerified() {
		return verified;
	}

	public void setMentions(String mentions) {
		this.mentions = mentions;
	}
	
	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}
        
        public void setVerified(String verified) {
		this.verified = verified;
	}
        
        public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
        public String getImageURL() {
		return imageurl;
	}

	public void setImageURL(String imageurl) {
		this.imageurl = imageurl;
	}
        
       


       
}