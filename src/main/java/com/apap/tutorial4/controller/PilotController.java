package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("navTitle", "Home Tutorial 5");
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("navTitle", "Add Pilot");
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method=RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot, Model model) {
		model.addAttribute("navTitle", "Add Pilot");
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		model.addAttribute("navTitle", "View Pilot");
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("flightList", pilot.getPilotFlight());
		model.addAttribute("pilot", pilot);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method=RequestMethod.GET)
	private String update(@PathVariable String licenseNumber, Model model) {
		model.addAttribute("navTitle", "Update Pilot");
		PilotModel oldPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("oldPilot", oldPilot);
		model.addAttribute("newPilot", new PilotModel());
		return "updatePilot";
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method=RequestMethod.POST)
	private String updatePilotSubmit(@ModelAttribute PilotModel newPilot, @PathVariable String licenseNumber, Model model) {
		model.addAttribute("navTitle", "Update Pilot");
		pilotService.updatePilot(newPilot, licenseNumber);
		return "update";
	}
	
	@RequestMapping(value = "/pilot/delete/{id}", method=RequestMethod.GET)
	private String deletePilot(@PathVariable long id, Model model) {
		model.addAttribute("navTitle", "Delete Pilot");
		pilotService.deletePilot(id);
		return "delete";
	}
	
	
}
