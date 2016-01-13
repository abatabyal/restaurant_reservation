package io.rest.exception;

public class AppException extends Exception{

	private static final long serialVersionUID = -2388998440873181354L;

		public AppException(String msg) {
			super(msg);
		}
		
		public AppException (String msg, Throwable cause) {
			super(msg, cause);
		}

	}



