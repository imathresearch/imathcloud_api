package com.util;


import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class HttpGet extends Http {
	
	public HttpGet (String finalUrl, final AuthenticUser auser)  throws IOException{
		
		//Authetication is required
		/*
    	Authenticator.setDefault(new Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication(auser.getuserName(), auser.getsecretKey().toCharArray());
    	      }
    	});*/
    			
		
		URL url = new URL(finalUrl);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		String authetication = auser.getuserName() + ":" + auser.getsecretKey();
		String encoding =  javax.xml.bind.DatatypeConverter.printBase64Binary(authetication.getBytes("UTF-8"));		
		conn.setRequestProperty("Authorization", "Basic " + encoding);
		conn.connect();
		
		
	}
	


}
