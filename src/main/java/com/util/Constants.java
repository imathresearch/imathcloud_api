package com.util;

public class Constants {
	
	static public final String IMATHCLOUD_PORT = "8080";     // In Development 
    //static public final String IMATHCLOUD_PORT = "80";       // In Production

	static public final String HTTP = "http://";
	static public final String HOST_IMATHCLOUD = "localhost";		//In development
	
	//static public final String IMATHCLOUD_NEWSESSION_SERVICE = "iMathCloud/rest/beta/api/exec/newSession";
	static public final String IMATHCLOUD_NEWSESSION_SERVICE = "iMathCloud/rest/session_service/newSession";
	static public final String IMATHCLOUD_GETJOBS_SERVICE = "iMathCloud/rest/job_service/getJobs";
	static public final String IMATHCLOUD_UPLOADFILE_SERVICE = "iMathCloud/rest/beta/api/data/upload";
	//static public final String IMATHCLOUD_RUNPYTHONJOB_SERVICE = "iMathCloud/rest/jobpython_service/submitJob";
	static public final String IMATHCLOUD_RUNPYTHONJOB_SERVICE = "iMathCloud/rest/beta/api/exec/jobpython/exec";
	static public final String IMATHCLOUD_GETJOB_SERVICE = "iMathCloud/rest/job_service/getJob";
	static public final String IMATHCLOUD_GETOUTPUTFILES_SERVICE = "iMathCloud/rest/job_service/getJobOutputFiles";
	static public final String IMATHCLOUD_GETFILECONTENT_SERVICE = "iMathCloud/rest/file_service/getFileContent";
	static public final String IMATHCLOUD_STOPJOB_SERVICE = "iMathCloud/rest/beta/api/exec/stop";
	static public final String IMATHCLOUD_REGISTERUSER_SERVICE = "iMathCloud/rest/beta/api/user/newUser";
}
