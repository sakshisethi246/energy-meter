package com.standard.charted.energy.exception;

import lombok.Data;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
public class CustomRuntimeException extends RuntimeException {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The error code. */
	private String errorCode;

	/** The error message. */
	private String errorMessage;

	/** The exception. */
	private Exception exception;

	/**
	 * Instantiates a new custom runtime exception.
	 *
	 * @param errorCode    the error code
	 * @param errorMessage the error message
	 * @param exception    the exception
	 */
	public CustomRuntimeException(String errorCode, String errorMessage, Exception exception) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exception = exception;
	}

}
