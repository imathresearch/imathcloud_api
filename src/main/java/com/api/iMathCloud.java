package com.api;

import com.util.AuthenticUser;
import com.util.Constants;
import com.util.HttpGet;
import com.util.HttpPost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class iMathCloud {
	

	public static boolean requestSession(AuthenticUser auser){
		boolean success = false;
		
		List<String> param = new ArrayList<String> ();
		param.add(auser.getuserName());
		
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_NEWSESSION_SERVICE, param);
		
		//AuthenticUser auser = new AuthenticUser(userName, "h1i1m1");
		
		try {
			HttpGet hGet = new HttpGet(finalURL, auser);
			if(hGet.getResponseCode() == 200){
				success = true;
			}
			else{
				success = false;
			}
		} catch (IOException e1) {
			System.out.println("Error in REST call: url:" + finalURL);
			success = false;
		}
		
		return success;
		
		/*HttpURLConnection conn;
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
		
		return success;*/
	}
	
	public static String getJobs(AuthenticUser auser){
		
		List<String> param = new ArrayList<String> ();
		param.add(auser.getuserName());
				
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_GETJOBS_SERVICE, param);
		
		//AuthenticUser auser = new AuthenticUser(userName, "h1i1m1");
		String jobs = new String();
		try {
			HttpGet hGet = new HttpGet(finalURL, auser);
			
			if(hGet.getResponseCode() == 200){
				jobs = hGet.getResultFromServer();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return jobs;
		
		/*HttpURLConnection conn;
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
		
		return jobs;*/
	}
	
	
	public static boolean uploadFile(String userName, String fileName, String location){
		
		List<String> param = new ArrayList<String> ();
		
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_UPLOADFILE_SERVICE, param);
		
		AuthenticUser auser = new AuthenticUser(userName, "h1i1m1");
		
		boolean success = false;
		try{
			HttpPost hPost = new HttpPost (finalURL, auser);
			hPost.sendFile(fileName, location);
			if(hPost.getResponseCode() == 200){
				System.out.println(hPost.getResultFromServer());
				success = true;
			}
			else{
				success = false;
			}
		}
		catch(Exception e){
			System.out.println("Error uploading file");
			success = false;
		}
		
		return success;
	}
	
   /* private static HttpURLConnection makeAJAXCall_GET(String finalURL) throws Exception{
    	
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
    }*/
    
    private static String generateURLforiMathCloud(String rest_service, List<String> params){
    	
    	String formatedParams = new String ();
    	for(int i = 0; i < params.size(); i++){
    		formatedParams = formatedParams.concat(params.get(i)).concat("/");		
    	}
    	
    	String finalURL = Constants.HTTP + 
                Constants.HOST_IMATHCLOUD +  
                ":" + Constants.IMATHCLOUD_PORT + 
                "/" + rest_service +
                "/" + formatedParams;
        return finalURL;
    }
	
}
