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


/**
* @author kumara
*
*/
public class HttpPost {
URL u;



/**
*
*/

/**
* @param url
* Url to send data to
* @param file1
* path to local file to upload
* @return int if 0 then all ok else see value and look in code!
*/
public int sendFile(String surl, String file1) {

int rtn = 1;

HttpURLConnection conn = null;
BufferedReader br = null;
DataOutputStream dos = null;
DataInputStream inStream = null;

InputStream is = null;
OutputStream os = null;
boolean ret = false;
String StrMessage = "";
String exsistingFileName = file1;
File fFile2Snd = new File(exsistingFileName);

String lineEnd = "\r\n";
String twoHyphens = "--";
String boundary = "***232404jkg4220957934FW**";

int bytesRead, bytesAvailable, bufferSize;

byte[] buffer;

int maxBufferSize = 1 * 1024 * 1024;

String responseFromServer = "";

String urlString = surl;// "http://localhost:81/FileUpload/requestupload";
// urlString =
// "http://a.com/sel2in/prjs/php/p12/skewmypic/v1/getBytes.php";

try {
// ------------------ CLIENT REQUEST

FileInputStream fileInputStream = new FileInputStream(new File(
exsistingFileName));
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

conn.setRequestProperty("Content-Type",
"multipart/form-data;boundary=" + boundary);

dos = new DataOutputStream(conn.getOutputStream());

dos.writeBytes(twoHyphens + boundary + lineEnd);
dos.writeBytes("Content-Disposition: form-data; name=uploadedFile;"
+ " filename=\"" + fFile2Snd.getName() + "\"" + lineEnd);
dos.writeBytes(lineEnd);






/*.append(LINE_FEED);
writer.append("Content-Type: text/plain; charset=" + charset).append(
        LINE_FEED);
writer.append(LINE_FEED);
writer.append(value).append(LINE_FEED);
*/
rtn++;

// create a buffer of maximum size

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

// send multipart form data necesssary after file data...

//dos.writeBytes(lineEnd);
//dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

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

// ------------------ read the SERVER RESPONSE

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


}

