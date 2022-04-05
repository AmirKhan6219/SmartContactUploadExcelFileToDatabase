package com.smarts.controller;

import com.smarts.dto.ContactDto;
import com.smarts.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.smarts.service.ContactServiceImpl;

@RestController
public class ExternalController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ContactServiceImpl contactService;

	@Autowired
	private ExternalService externalService;

	@GetMapping("/fetch-all/{isRemote}")
	public ResponseEntity<String> fetchAll(@PathVariable("isRemote") String isRemote) {

		return externalService.fetchAll(isRemote);
	}

	@PostMapping("/postData/{isRemote}")
	public ResponseEntity<String> postData(@PathVariable("isRemote") String isRemote, @RequestBody ContactDto contactDto) {
		return externalService.postData(isRemote, contactDto);

	}
}


