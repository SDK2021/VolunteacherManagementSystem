package com.volunteacher.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class Error {

		private Date date;
		private HttpStatus httpStatus;
		private String message;
		private String path;
		
		public Error() {
			super();
		}
		
		public Error(Date date, HttpStatus httpStatus, String message, String path) {
			super();
			this.date = date;
			this.httpStatus = httpStatus;
			this.message = message;
			this.path = path;
		}


		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
		
		public HttpStatus getHttpStatus() {
			return httpStatus;
		}
		
		public void setHttpStatus(HttpStatus httpStatus) {
			this.httpStatus = httpStatus;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String getPath() {
			return path;
		}
		
		public void setPath(String path) {
			this.path = path;
		}
		
}