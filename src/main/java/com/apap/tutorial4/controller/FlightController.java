package com.apap.tutorial4.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value="/flight/add/{licenseNumber}",method=RequestMethod.GET)
	private String add(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
		model.addAttribute("navTitle", "Add Flight");
		
		PilotModel pilotTemp = new PilotModel();
		pilotTemp.setPilotFlight(new ArrayList<FlightModel>());
		pilotTemp.getPilotFlight().add(new FlightModel());
		
		model.addAttribute("licenseNumber", licenseNumber);
		model.addAttribute("pilotTemp", pilotTemp);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}",method=RequestMethod.POST, params={"addRow"})
	private String addRow(@PathVariable(value="licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilotTemp, Model model) {
		model.addAttribute("navTitle", "Add Flight");
		
		FlightModel flight = new FlightModel();
		pilotTemp.getPilotFlight().add(flight);
		
		model.addAttribute("licenseNumber", licenseNumber);
		model.addAttribute("pilotTemp", pilotTemp);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}",method=RequestMethod.POST, params={"save"})
	private String addFlightSubmit(@PathVariable(value="licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilotTemp, Model model) {
		model.addAttribute("navTitle", "Add Flight");
		
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		for (FlightModel flight : pilotTemp.getPilotFlight()) {
			flight.setPilot(pilot);
			flightService.addFlight(flight);
		}
		return "add";
	}
	
	@RequestMapping("/flight/view")
	public String view(@RequestParam("flightNumber") String flightNumber, Model model) {
		model.addAttribute("navTitle", "View Flight");
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", flight.getPilot());
		return "view-flight";
	}
	
	@RequestMapping(value = "/flight/update/{flightNumber}", method=RequestMethod.GET)
	private String update(@PathVariable String flightNumber, Model model) {
		model.addAttribute("navTitle", "View Flight");
		FlightModel oldFlight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("oldFlight", oldFlight);
		model.addAttribute("newFlight", new FlightModel());
		return "updateFlight";
	}
	
	@RequestMapping(value = "/flight/update/{flightNumber}", method=RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel newFlight, @PathVariable String flightNumber, Model model) {
		model.addAttribute("navTitle", "Update Flight");
		flightService.updateFlight(newFlight, flightNumber);
		return "update";
	}
	
	@RequestMapping(value = "/flight/delete", method=RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		model.addAttribute("navTitle", "Delete Flight");
		for (FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		return "delete";
	}
	
	@RequestMapping(value = "/flight/delete/{flightId}", method=RequestMethod.GET)
	private String deleteOneFlight(@PathVariable Long flightId, Model model) {
		flightService.deleteFlight(flightId);
		return "delete";
	}
	
	
}
