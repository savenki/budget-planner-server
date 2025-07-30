package com.atmakuruit.budgetPlanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atmakuruit.budgetPlanner.model.Planners;
import com.atmakuruit.budgetPlanner.model.TimePeriod;
import com.atmakuruit.budgetPlanner.service.PlannerService;
import com.atmakuruit.budgetPlanner.service.SegmentService;

@RestController
@RequestMapping("/budgetPlanner")
public class BudgetPlannerController {

	private final SegmentService segementService;
	private final PlannerService plannersService;
	public BudgetPlannerController(SegmentService segementService, PlannerService plannersService) {
		this.segementService = segementService;
		this.plannersService = plannersService;
	}


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
	
	@GetMapping("planners")
	public List<Planners> planners(){
		return this.plannersService.getPlanners();
	}
}
