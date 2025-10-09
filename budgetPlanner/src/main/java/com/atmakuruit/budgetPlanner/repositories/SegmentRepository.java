package com.atmakuruit.budgetPlanner.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atmakuruit.budgetPlanner.model.Planners;
import com.atmakuruit.budgetPlanner.model.Segment;
import com.atmakuruit.budgetPlanner.model.TimePeriod;

public interface SegmentRepository extends JpaRepository<Segment, Integer> {

	List<Segment> findByPlanners(Planners user);

	List<Segment> findByTimePeriod(TimePeriod timePeriod);

	List<Segment> findByTimePeriodId(Long timePeriodId);

	@Query("SELECT s FROM Segment s WHERE s.planners.userName = :username")
	List<Segment> findSegmentsByUsername(@Param("username") String username);

}
