package com.standard.charted.energy.request;

import java.util.List;

import lombok.Data;

/**
 * Instantiates a new store readings request.
 */
@Data
public class StoreReadingsRequest {

	/** The smart meter id. */
	private String smartMeterId;

	/** The electricity readings. */
	private List<ReadingInfo> electricityReadings;
}
