package com.atmakuruit.budgetPlanner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atmakuruit.budgetPlanner.model.Planners;
import com.atmakuruit.budgetPlanner.repositories.PlannersRepository;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@Log4j
public class PlannerService {

	private final PlannersRepository plannersRepository;
	public PlannerService(PlannersRepository plannersRepository) {
		this.plannersRepository = plannersRepository;
	}
	
	public List<Planners> getPlanners() {
	    List<Planners> planners = this.plannersRepository.findAll();

		
	    return planners;
	}
	
	public List<Planners> getPlannerByEmail(String valueOf){
		return this.plannersRepository.findByEmail(valueOf);
	}
	
	public String deletePlannerByEmail(String email) throws Exception {
		try {
			try {
				this.plannersRepository.deleteByEmail(email);
				return "Delete Successful";
			}catch(Exception e) {
				throw new Exception(e);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}
}
