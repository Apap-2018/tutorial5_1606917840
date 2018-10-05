package com.apap.tutorial4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDB;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDb;
	
//	kalo dioverride error
//	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
//	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
//	@Override
	public void deletePilot(long id) {
		pilotDb.deleteById(id);
	}
	
//	@Override
	public void updatePilot(PilotModel updatedPilot, String licenseNumber) {
		PilotModel oldPilot = getPilotDetailByLicenseNumber(licenseNumber);
		oldPilot.setName(updatedPilot.getName());
		oldPilot.setFlyHour(updatedPilot.getFlyHour());
	}
}
