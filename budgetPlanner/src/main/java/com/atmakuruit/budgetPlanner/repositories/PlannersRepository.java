/**
 * 
 */
package com.atmakuruit.budgetPlanner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atmakuruit.budgetPlanner.model.Planners;

/**
 * 
 */
public interface PlannersRepository extends JpaRepository<Planners, Integer> {

	public List<Planners> findByUserName(String valueOf);
	public List<Planners> findByEmail(String valueOf);
	void deleteById(Integer Id);
	void deleteByEmail(String email);
	void deleteByUserNameAndEmail(String userName, String email);
}
