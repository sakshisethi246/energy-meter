package com.standard.charted.energy.response;

import lombok.Data;

/**
 * Instantiates a new api response.
 */
@Data
public class ApiResponse {

	/** The status. */
	private String status;

	/** The code. */
	private String code;

	/** The data. */
	private Object data;

}
