package com.atmakuruit.budgetPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atmakuruit.budgetPlanner.model.TimePeriod;

public interface TimePeriodRepository extends JpaRepository<TimePeriod, Integer> {

	TimePeriod findByYearAndMonth(String year, String month);

	List<TimePeriod> findByYear(String valueOf);
}
