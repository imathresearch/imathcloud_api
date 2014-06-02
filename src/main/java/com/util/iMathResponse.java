package com.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class iMathResponse {
	
	static public class PublicResponse{
	
		private int code;
		private String resource;
		private String name;
		private Map <String, String> status;
		private List<Integer> pcts = new ArrayList<Integer>();
		
		public int getCode(){
			return this.code;
		}
		
		public String getResource(){
			return this.resource;
		}
		
		public String getName(){
			return this.name;
		}
		
		public Map<String, String> getStatus(){
			return this.status;
		}
		
		public List<Integer> getPcts(){
			return this.pcts;
		}
		
		public void setCode(int code){
			this.code = code;
		}
		
		public void setResource(String resource){
			this.resource = resource;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public void setStatus(Map<String, String> status){
			this.status = status;
		}
		
		public void setPcts(List<Integer> pcts){
			this.pcts = pcts;
		}
	}
	
	static public class JobDTO{
		
		private Long id;
		private Date startDate;
		private Date endDate;
		private String state;
		private String description;
		private Map<String, String> jobResult;
		private Map<String, List<String>> pcts;
		
		public Long getid(){
			return this.id;
		}
		
		public Date getStartDate(){
			return this.startDate;
		}
		
		public Date getEndDate(){
			return this.endDate;
		}
		
		public String getState(){
			return this.state;
		}
		
		public String getDescription(){
			return this.description;
		}
		
		public Map<String, String> getJobResult(){
			return this.jobResult;
		}
		
		public Map<String, List<String>> getPcts(){
			return this.pcts;
		}
		
		public void setId(Long id){
			this.id = id;
		}
		
		public void setStartDate(Date d){
			this.startDate = d;
		}
		
		public void setEndDate(Date d){
			this.endDate  = d;
		}
		
		public void setState(String state){
			this.state = state;
		}
		
		public void setDescription(String description){
			this.description = description;
		}
		
		public void setJobResult(Map<String, String> jobResult){
			this.jobResult = jobResult;
		}
		
		public void setPcts(Map<String, List<String>> pcts){
			this.pcts = pcts;
		}
	}
	
	static public class FileDTO{
		

		private Long id;
		private String name;
		
		
		public Long getId(){
			return this.id;
		}
		
		public String getName(){
			return this.name;
		}		
	
		public void setId(Long id){
			this.id = id;
		}
		
		public void setName(String name){
			this.name = name;
		}
	
	}
	
	static public class ContentFile{
		
		private Long id;
		private String name;
		private String type;
		private List<String> content;
		
		public Long getId(){
			return this.id;
		}
		
		public String getName(){
			return this.name;
		}
		
		public String getType(){
			return this.type;
		}
		
		public List<String> getContent(){
			return this.content;
		}
	
		public void setId(Long id){
			this.id = id;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public void setType(String type){
			this.type = type;
		}
		
		public void setContent(List<String> content){
			this.content = content;
		}
		
	}
}
