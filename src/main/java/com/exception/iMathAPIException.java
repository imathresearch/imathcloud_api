package com.exception;


public class iMathAPIException extends Exception{
	
	private static final long serialVersionUID = 1L;
	public static String ERR_NO_AVAILABLE_HOST = "No available host for job id:";
	public static enum API_ERROR {
		OTHER             ("E0000", "[0]"),
		NO_AVAILABLE_HOST ("E0001", "No available host for job id: [0]"),
		NO_SOURCE_FILES   ("E0002", "No source files for job id: [0]"),
		FILE_NOT_FOUND    ("E0003", "File id: [0] not found."),
		ANY_AVAILABLE_HOST ("E0004", "Any available host."),
		NOT_USER_IN_DB ("E0005", "User: [0] is not in the iMath Cloud Database"),
		JOB_DOES_NOT_EXISTS("E0006", "Job id: [0] not found"),
		NO_AUTHORIZATION ("E0007", "No authorization to access de resource"),
		JOB_NOT_IN_OK_STATE("E0008", "Job id: [0] is not in the FINISHED OK state"),
		RECOVER_PROBLEM ("E0009", "Fatal error on erasing file [0]. Not recovered"),
		INTERNAL_SERVER_ERROR ("E0010", "Internal Server Error");
		
		private final String code;
		private final String message;
		API_ERROR(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		String getMessage() {
			return "[" + this.code + "] - " + this.message;
		}
		
		String getCode() {
			return this.code;
		}
	}
	
	private String message;
	private API_ERROR apiError;
	
	public iMathAPIException(API_ERROR APIError, String [] args) {
		this.message = APIError.getMessage();
		this.apiError = APIError;
		if (args != null) {
			for(int i=0;i<args.length;i++) {
				this.message = this.message.replaceAll("[" + i + "]", args[i]);
			}
		}
	}
	
	public iMathAPIException(API_ERROR APIError) {
        this.message = APIError.getMessage();
        this.apiError = APIError;
        
    }
	
	public iMathAPIException(API_ERROR APIError, String arg) {
		this.message = APIError.getMessage().replaceAll("\\[0\\]", arg);
		this.apiError = APIError;
		
	}
	
	public iMathAPIException(API_ERROR APIError, String arg0, String arg1) {
		this.message = APIError.getMessage().replaceAll("[0]", arg0);
		this.message = this.message.replaceAll("[1]", arg1);
		this.apiError = APIError;
	}

	public iMathAPIException(API_ERROR APIError, String arg0, String arg1, String arg2) {
		this.message = APIError.getMessage().replaceAll("[0]", arg0);
		this.message = this.message.replaceAll("[1]", arg1);
		this.message = this.message.replaceAll("[2]", arg2);
		this.apiError = APIError;
	}

	public String getMessage() {
		return this.message;
	}
	
	public API_ERROR getAPI_ERROR() {
		return this.apiError; 
	}

}
