package com.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;


public class HttpPost extends Http {
	
	/**
	 * Constructor
	 * @throws IOException 
	 **/
	public HttpPost (String finalUrl, final AuthenticUser auser) throws IOException{
		
		//Authetication is required
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(auser.getuserName(), auser.getsecretKey().toCharArray());
			}
		});
		
		URL url = new URL(finalUrl);
		
		// Open a HTTP connection to the URL
		conn = (HttpURLConnection) url.openConnection();

		// Allow Inputs
		conn.setDoInput(true);

		// Allow Outputs
		conn.setDoOutput(true);

		// Don't use a cached copy.
		conn.setUseCaches(false);

		// Use a post method.
		conn.setRequestMethod("POST");
		
		// Basic request Property
		conn.setRequestProperty("Connection", "Keep-Alive");
		
		// request Property when you send json
		//conn.setRequestProperty("Content-Type", "application/json; charset=utf8");

		
		
		
		
	}
		

	/**
	 * @param url
	 * Url to send data to
	 * @param file1
	 * path to local file to upload
	 * @return int if 0 then all ok else see value and look in code!
	 */
	public int sendFile2(String surl, String fileName) {

		int rtn = 1;

		HttpURLConnection conn = null;
		DataOutputStream dos = null;

		String fileToUpload = fileName;
		File file2Snd = new File(fileToUpload);

		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "***232404jkg4220957934FW**";

	
		String urlString = surl;

		try {
			// ------------------ CLIENT REQUEST

			FileInputStream fileInputStream = new FileInputStream(new File(fileToUpload));
			rtn++;

			//Authetication is required
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("ammartinez", "h1i1m1".toCharArray());
				}
			});

			// open a URL connection to the Servlet

			URL url = new URL(urlString);
			rtn++;

			// Open a HTTP connection to the URL
			conn = (HttpURLConnection) url.openConnection();

			// Allow Inputs
			conn.setDoInput(true);

			// Allow Outputs
			conn.setDoOutput(true);

			// Don't use a cached copy.
			conn.setUseCaches(false);

			// Use a post method.
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Connection", "Keep-Alive");

			conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);

			dos = new DataOutputStream(conn.getOutputStream());

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=uploadedFile;"
					+ " filename=\"" + file2Snd.getName() + "\"" + lineEnd);
			dos.writeBytes(lineEnd);

			rtn++;

			// create a buffer of maximum size
			int maxBufferSize = 1 * 1024 * 1024;
			byte[] buffer;
			int bytesRead, bytesAvailable, bufferSize;
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// read file and write it into form...
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			String dir = "/";
			dos.writeBytes("Content-Disposition: form-data; name=destinationDir;");
			dos.writeBytes(lineEnd);
			dos.writeBytes("Content-Type: text/plain; charset=UTF-8");
			dos.writeBytes(lineEnd);
			dos.writeBytes(lineEnd);
			dos.writeBytes(dir);
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			// close streams

			fileInputStream.close();
			dos.flush();
			dos.close();

		} catch (MalformedURLException ex) {
			System.out.println("From ServletCom2 CLIENT REQUEST:" + ex);
		}

		catch (IOException ioe) {
			System.out.println("From ServletCom2 CLIENT REQUEST:" + ioe);
		}

		// 	------------------ read the SERVER RESPONSE

		try {
			System.out.println("Server response is: \n");
			//read the result from the server
			BufferedReader rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = rd.readLine()) != null){
				sb.append(line + '\n');
			}

			System.out.println(sb.toString());


			System.out.println("\nEND Server response ");

		} catch (IOException ioex) {
			System.out.println("From (ServerResponse): " + ioex);

		}
		rtn = 0;
		return rtn;

	}
	
	
	public void sendFile(String fileName, String location) {
		
		DataOutputStream dos = null;

		String fileToUpload = fileName;
		File file2Snd = new File(fileToUpload);

		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "***232404jkg4220957934FW**";

		try {
			// ------------------ CLIENT REQUEST

			FileInputStream fileInputStream = new FileInputStream(new File(fileToUpload));

			conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);

			dos = new DataOutputStream(conn.getOutputStream());

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=uploadedFile;"
					+ " filename=\"" + file2Snd.getName() + "\"" + lineEnd);
			dos.writeBytes(lineEnd);

			// create a buffer of maximum size
			int maxBufferSize = 1 * 1024 * 1024;
			byte[] buffer;
			int bytesRead, bytesAvailable, bufferSize;
			bytesAvailable = fileInputStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// read file and write it into form...
			bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			}

			dos.writeBytes(twoHyphens + boundary + lineEnd);
			if(!location.isEmpty()){
				String dir = location;
				dos.writeBytes("Content-Disposition: form-data; name=destinationDir;");
				dos.writeBytes(lineEnd);
				dos.writeBytes("Content-Type: text/plain; charset=UTF-8");
				dos.writeBytes(lineEnd);
				dos.writeBytes(lineEnd);
				dos.writeBytes(dir);
				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			}
			
			// close streams
			fileInputStream.close();
			dos.flush();
			dos.close();

		} catch (MalformedURLException ex) {
			System.out.println("From CLIENT REQUEST:" + ex);
		}

		catch (IOException ioe) {
			System.out.println("From CLIENT REQUEST:" + ioe);
		}
		

		// 	------------------ read the SERVER RESPONSE
		/*
		try {
			System.out.println("Server response is: \n");
			//read the result from the server
			BufferedReader rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = rd.readLine()) != null){
				sb.append(line + '\n');
			}

			System.out.println(sb.toString());


			System.out.println("\nEND Server response ");

		} catch (IOException ioex) {
			System.out.println("From (ServerResponse): " + ioex);

		}*/

}


}

