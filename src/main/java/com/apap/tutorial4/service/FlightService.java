package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	void deleteFlight(long id);
	void updateFlight(FlightModel newFlight, String flightNumber);
}
