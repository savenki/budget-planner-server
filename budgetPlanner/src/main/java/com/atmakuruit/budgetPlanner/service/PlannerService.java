package com.atmakuruit.budgetPlanner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atmakuruit.budgetPlanner.model.Planners;
import com.atmakuruit.budgetPlanner.repositories.PlannersRepository;

@Service
public class PlannerService {

	private final PlannersRepository plannersRepository;
	public PlannerService(PlannersRepository plannersRepository) {
		this.plannersRepository = plannersRepository;
	}
	
	public List<Planners> getPlanners(){
		return this.plannersRepository.findAll();
	}
	
	public List<Planners> getPlannerByEmail(String valueOf){
		return this.plannersRepository.findByEmail(valueOf);
	}
	
	public String deletePlannerByEmail(String email) throws Exception {
		try {
			this.plannersRepository.deleteByEmail(email);
			return "Delete Successful";
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
}
