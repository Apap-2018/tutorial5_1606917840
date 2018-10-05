package com.apap.tutorial4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDB;


@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;

//	kalo dioverride error
//	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
//	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}

//	@Override
	public void deleteFlight(long id) {
		flightDb.deleteById(id);
	}

//	@Override
	public void updateFlight(FlightModel updatedFlight, String flightNumber) {
		FlightModel oldFlight = getFlightDetailByFlightNumber(flightNumber);
		oldFlight.setOrigin(updatedFlight.getOrigin());
		oldFlight.setDestination(updatedFlight.getDestination());
		oldFlight.setTime(updatedFlight.getTime());
	}
	
}
