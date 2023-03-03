package com.skiResort.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skiResort.server.entity.SkiLiftRideEvent;

@Controller
@RequestMapping("/skiers")
public class SkiResortController {
	
	@PostMapping
	ResponseEntity<Map> saveSkiers(@RequestBody SkiLiftRideEvent liftRideEvent){
		Map<String,String> resultMap = validateInput(liftRideEvent);
		HttpStatus status = HttpStatus.OK;
		if(resultMap.containsKey("error")){
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(resultMap);
	}
	
	private Map<String,String> validateInput(SkiLiftRideEvent liftRideEvent){
		Map<String,String> resultMap = new HashMap<>();
		if(liftRideEvent.getSkierID() < 1 || liftRideEvent.getSkierID() > 100000) {
			resultMap.put("error", "invalid skierId");
		}else if(liftRideEvent.getResortID() < 1 || liftRideEvent.getResortID() > 10) {
			resultMap.put("error", "invalid resortId");
		}else if(liftRideEvent.getLiftID() < 1 || liftRideEvent.getLiftID() > 40) {
			resultMap.put("error", "invalid liftId");
		}else if(liftRideEvent.getSeasonID() != 2022) {
			resultMap.put("error", "invalid seasonId");
		}else if(liftRideEvent.getDayID() != 1) {
			resultMap.put("error", "invalid dayId");
		}else if(liftRideEvent.getTime() < 1 || liftRideEvent.getTime() > 360) {
			resultMap.put("error", "invalid time");
		}else {
			resultMap.put("success", "record saved");
		}
		return resultMap;
	}

}
