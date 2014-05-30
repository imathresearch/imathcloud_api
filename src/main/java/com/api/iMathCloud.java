package com.api;

import java.util.Map;

import org.codehaus.jackson.map.DeserializationConfig;

import com.util.AuthenticUser;
import com.util.Constants;
import com.util.HttpGet;
import com.util.HttpPost;
import com.util.iMathResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;



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
	}
	
	
	public static Long uploadFile(String userName, String fileName, String location){
		
		//1. Create the URL
		List<String> param = new ArrayList<String> ();		
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_UPLOADFILE_SERVICE, param);

		//2. Create the user to be authenticated in the REST call
		AuthenticUser auser = new AuthenticUser(userName, "h1i1m1");
		
		Long idFile = 0L;
		try{
			//3. Perform the REST call
			HttpPost hPost = new HttpPost (finalURL, auser);
			hPost.sendFile(fileName, location);
			
			//4. Manage the answer of the REST CALL
			if(hPost.getResponseCode() == 200){
				//5. If OK, map the JSON string to an iMathResponse object
				ObjectMapper mapper = new ObjectMapper();
				List<iMathResponse.PublicResponse> response = mapper.readValue(hPost.getResultFromServer(), new TypeReference<List<iMathResponse.PublicResponse>>() { });
				//6. Get the id of the uploaded file {'resource':'data/idFile'}
				String resource = response.get(0).getResource();
				String[] resource_split = resource.split("/");
				if(!resource_split[1].equals("null")){
					idFile = Long.parseLong(resource_split[1]);
				}			
			}
		}
		catch(Exception e){
			System.out.println("Error uploading file");
			System.out.println(e.getMessage());
		}
		
		return idFile;
	}
	
	public static Long runPythonJob(String userName, Long idFile){
		
		Long idJob = 0L;
		
		//1. Create the URL
		List<String> param = new ArrayList<String> ();
		param.add(userName);
		param.add(String.valueOf(idFile));
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_RUNPYTHONJOB_SERVICE, param);
		
		//2. Create the user to be authenticated in the REST call
		AuthenticUser auser = new AuthenticUser(userName, "h1i1m1");
		
		try{
			//3. Perform the REST call
			HttpPost hPost = new HttpPost (finalURL, auser);
			
			//4. Manage the answer of the REST CALL
			if(hPost.getResponseCode() == 200){
				//5. If OK, map the JSON string to an iMathResponse object
				ObjectMapper mapper = new ObjectMapper();
				String results = hPost.getResultFromServer();
				iMathResponse.PublicResponse response = mapper.readValue(results, iMathResponse.PublicResponse.class);
				//6. Get the id of the uploaded file {'resource':'data/idFile'}
				String resource = response.getResource();
				String[] resource_split = resource.split("/");
				if(!resource_split[1].equals("null")){
					idJob = Long.parseLong(resource_split[1]);
				}			
			}
		}
		catch(Exception e){
			System.out.println("Error submitting python job");
			System.out.println(e.getMessage());
		}
		
		return idJob;
		
	}
	
	public static String getJobState(String userName, Long idJob){
		
		String state = null;
		
		//1. Create the URL
		List<String> param = new ArrayList<String> ();
		param.add(String.valueOf(idJob));				
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_GETJOB_SERVICE, param);
		
		//2. Create the user to be authenticated in the REST call
		AuthenticUser auser = new AuthenticUser(userName, "h1i1m1");
		
		String job; 
		try {
			//3. Perform the REST call
			HttpGet hGet = new HttpGet(finalURL, auser);
			
			//4. Manage the answer of the REST CALL
			if(hGet.getResponseCode() == 200){
				job = hGet.getResultFromServer();
				System.out.println("JOB " + job);
				ObjectMapper mapper = new ObjectMapper();
				iMathResponse.JobDTO response = mapper.readValue(job, iMathResponse.JobDTO.class);
				state = response.getState();
				System.out.println("JOB STATE " + state);
				
			}
		} catch (IOException e) {
			System.out.println("Error getting job " + idJob);
			System.out.println(e.getMessage());
		}
		
		
		return state;
	}
 
	
	public static Map<String, Long> getJobOutputFiles(String userName, Long idJob){
		
		Map<String, Long> map_outputfiles = new HashMap<String, Long>();
		
		//1. Create the URL
		List<String> param = new ArrayList<String> ();
		param.add(String.valueOf(idJob));				
		String finalURL = generateURLforiMathCloud(Constants.IMATHCLOUD_GETOUTPUTFILES_SERVICE, param);
				
		//2. Create the user to be authenticated in the REST call
		AuthenticUser auser = new AuthenticUser(userName, "h1i1m1");
				
		String outputfiles = null;
		try {
			//3. Perform the REST call
			HttpGet hGet = new HttpGet(finalURL, auser);
			
			//4. Manage the answer of the REST CALL
			if(hGet.getResponseCode() == 200){
				outputfiles = hGet.getResultFromServer();
				System.out.println("OUTPUTFILES " + outputfiles);
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				List<iMathResponse.FileDTO> response = mapper.readValue(outputfiles, new TypeReference<List<iMathResponse.FileDTO>>(){});
				
				for(iMathResponse.FileDTO f : response){
					map_outputfiles.put(f.getName(), f.getId());
				}						
			}
		} catch (IOException e) {
			System.out.println("Error getting outputfiles of job " + idJob);
			System.out.println(e.getMessage());
		}
		
		
		return map_outputfiles;
	}
    
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
