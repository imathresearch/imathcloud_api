package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public abstract class Http {

	protected HttpURLConnection conn = null;
		
	public int getResponseCode() throws IOException{
		return conn.getResponseCode();
	}
	
	public String getResultFromServer() throws IOException{
		String result = new String();
		BufferedReader rd  = null;
	    StringBuilder sb = null;
		//read the result from the server
        rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        sb = new StringBuilder();
        String line = new String();
        while ((line = rd.readLine()) != null){
              sb.append(line + '\n');
        }
        
        System.out.println(sb.toString());
        result = sb.toString();
        return result;
	}
}
