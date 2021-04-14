package com.hw.exception;

public class ReportNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportNotFoundException(String msg) {
		super(msg);
	}
	
	public ReportNotFoundException() {
		super();
	}
	
	public ReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
