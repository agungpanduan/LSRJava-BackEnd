package com.lsr.controller;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsr.response.CommonResponse;

@CrossOrigin
@RestController
@RequestMapping(value = "api/lsr_enhance/main/v1")
public class LsrInputController {

	public LsrInputController() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("LsrInput")
	public ResponseEntity<CommonResponse> findAll(@RequestHeader("Authorization") String token//,
			) {//@RequestBody UserRequest userRequest

//		String searchValue = userRequest.getSearchValue().strip();
//		List<UserRequest> listData = userService.searchData(
//				PageRequest.of(userRequest.getPageNumber(), userRequest.getPageSize()), searchValue,
//				userRequest.getStatus());
//
		CommonResponse response = new CommonResponse();
		response.setMessage("tesst");
//
//		response.setStatus("success");
//		response.setMessage("Proccessed Successfully");
//
//		response.setData(listData);
//		response.setCountData(listData.size());

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
