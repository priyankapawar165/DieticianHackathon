package com.ninja.dietician.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.dietician.entity.Morbidity;
import com.ninja.dietician.service.MorbidityService;

@RestController
@RequestMapping("/api")
public class MorbidityController {

	@Autowired
	MorbidityService service;
	
	

	@GetMapping("/morbidity")
	public ResponseEntity<List<Morbidity> > getAllMorbidties() {
		return ResponseEntity.ok(service.getMorbidities());
	}

	@PostMapping("/morbidity")
	public ResponseEntity<Morbidity> postBody(@RequestBody Morbidity morbidity) {
		Morbidity createdMorbidity =  service.addMorbidity(morbidity);
		return new ResponseEntity<>(createdMorbidity, HttpStatus.CREATED);
		//return ResponseEntity.ok(service.addMorbidity(morbidity));

	}

	@DeleteMapping("/morbidity/{morbidityName}/{morbidityTestId}")
	public ResponseEntity<String> deleteMorbidities(@PathVariable("morbidityName") String morbidityName, @PathVariable("morbidityTestId") String morbidityTestId) {
		return ResponseEntity.ok(service.deleteMorbidities(morbidityName, morbidityTestId));
	}

	@PutMapping("/morbidity/{morbidityName}/{morbidityTestId}")
	public ResponseEntity<Object> updateMorbidities(@PathVariable("morbidityName") String morbidityName, @PathVariable("morbidityTestId") String morbidityTestId,
			@RequestBody Morbidity morbidity) {
		return ResponseEntity.ok(service.updateMorbidities(morbidityName, morbidityTestId, morbidity));
	}
	

	@GetMapping("/getMorbiditybyName/{morbidityName}")
	public ResponseEntity<Object> getMorbiditybyName(@PathVariable("morbidityName") String morbidityName) {
		return ResponseEntity.ok(service.getMorbiditybyName(morbidityName));
	}
	

	@GetMapping("/getMorbiditybyMorbidityTestId/{morbidityTestId}")
	public ResponseEntity<Object> getMorbiditybyMorbidityTestId( @PathVariable("morbidityTestId") String morbidityTestId) {
		return ResponseEntity.ok(service.getMorbiditybyMorbidityTestId( morbidityTestId));
	}

}
