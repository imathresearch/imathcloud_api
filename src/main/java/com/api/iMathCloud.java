package com.api;

import com.util.Constants;
import com.util.MultipartUtility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



public class iMathCloud {
	


	public static boolean requestSession(String userName){
		boolean success = false;
		
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_NEWSESSION_SERVICE, userName);
		
		HttpURLConnection conn;
		try{
			conn = makeAJAXCall_GET(finalURL);
			if(conn.getResponseCode() == 200){
				success = true;
			}
			else{
				success = false;
			}
		}
		catch(Exception e){
			System.out.println("Error in AJAX call: url:" + finalURL);
			success = false;
		}
		
		return success;
	}
	
	public static String getJobs(String userName){
		
		
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_GETJOBS_SERVICE, userName);
		
		HttpURLConnection conn;
		BufferedReader rd  = null;
	    StringBuilder sb = null;
	    String line = null;
	    String jobs = new String();
		try{
			conn = makeAJAXCall_GET(finalURL);
			if(conn.getResponseCode() == 200){
				
				//read the result from the server
		        rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        sb = new StringBuilder();
		        
		        while ((line = rd.readLine()) != null){
		              sb.append(line + '\n');
		        }
		        
		        System.out.println(sb.toString());
		        jobs = sb.toString();
		 				
			}
			
		}
		catch(Exception e){
			System.out.println("Error in AJAX call: url:" + finalURL);
			
		}
		
		return jobs;
	}
	
	
	public static boolean uploadFile(String fileName){
		
		boolean success = true;
		
		String charset = "UTF-8";
        File uploadFile = new File(fileName);        
        String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_UPLOADFILE_SERVICE, "");
 
        try {
            MultipartUtility multipart = new MultipartUtility(finalURL, charset);
            
            System.out.println("multipart1");
            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");
            System.out.println("multipart2");
            multipart.addFormField("destinationDir", "/src");
            System.out.println("multipart3");            
            multipart.addFilePart("uploadedFile", uploadFile);
            System.out.println("multipart4");
            List<String> response = multipart.finish();
            System.out.println("multipart5");
            System.out.println("SERVER REPLIED:");
             
            for (String line : response) {
                System.out.println(line);
            }
            success = true;
        } catch (IOException ex) {
            System.out.println(ex);
            success = false;
        }
		
		return success; 
	}
	
    private static HttpURLConnection makeAJAXCall_GET(String finalURL) throws Exception{
    	
    	//Authetication is required
    	Authenticator.setDefault(new Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication("ammartinez", "h1i1m1".toCharArray());
    	      }
    	});
    	
		URL url = new URL(finalURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		con.connect();
	
		return con;
    }
    
    private static HttpURLConnection makeAJAXCall_POST(String finalURL) throws Exception{    	
		URL url = new URL(finalURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		//add request header
		con.setRequestMethod("POST");
		
		//To change 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		con.connect();
		
		return con;
    }
    
    private static String generateURLforiMathCloud(String rest_service, String params){
    	String finalURL = Constants.HTTP + 
                Constants.HOST_IMATHCLOUD +  
                ":" + Constants.IMATHCLOUD_PORT + 
                "/" + rest_service +
                "/" + params;
        return finalURL;
    }
	
}
