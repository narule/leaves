package com.domoment.leaves.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domoment.leaves.common.util.AccessRecord;

@RestController
@RequestMapping("/access_record")
public class AccessRecordController {
	
	private static final String CERTIFICATE = "hrwe#$&*^*@salkdjfJKHSLKSQHJHDAK9d8238912009)(w9#H";
	@PostMapping("/info")
	public Object getAccessRecords(@RequestBody String certificate) {
		if(CERTIFICATE.equals(certificate)) {
			return AccessRecord.view_access_records();
		}else {
			return "error";
		}
	}
}
