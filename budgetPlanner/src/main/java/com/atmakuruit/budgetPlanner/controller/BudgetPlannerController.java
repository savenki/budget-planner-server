package com.atmakuruit.budgetPlanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atmakuruit.budgetPlanner.model.TimePeriod;
import com.atmakuruit.budgetPlanner.service.SegmentService;

@RestController
@RequestMapping("/budgetPlanner")
public class BudgetPlannerController {

	public BudgetPlannerController() {
	}

	@Autowired
	SegmentService segementService;

	@GetMapping
	public String welcome() {
		return "hello world";
	}

	@PostMapping("seedFiveYearsFromCurrent")
	public String seedFiveYearsFromCurrent() throws IllegalStateException {
		return this.segementService.seedFiveYearsFromCurrent();
	}
	
	@GetMapping("timePeriod")
	public List<TimePeriod> timePeriod(){
		return this.segementService.timePeriod();
	}
}
