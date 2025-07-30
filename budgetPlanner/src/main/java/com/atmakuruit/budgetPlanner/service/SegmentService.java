package com.atmakuruit.budgetPlanner.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.atmakuruit.budgetPlanner.model.TimePeriod;
import com.atmakuruit.budgetPlanner.repositories.TimePeriodRepository;

@Service
public class SegmentService {
	
	private final TimePeriodRepository timePeriodRepository;
	public SegmentService(TimePeriodRepository timePeriodRepository) {
		this.timePeriodRepository = timePeriodRepository;
	}
	
	public String seedFiveYearsFromCurrent() throws IllegalStateException {
		int currentYear = LocalDate.now().getYear();

        // Check if current year already exists
        List<TimePeriod> existing = timePeriodRepository.findByYear(String.valueOf(currentYear));
        if (!existing.isEmpty()) {
            throw new IllegalStateException("Time periods for current year already exist");
        }

        for (int year = currentYear; year < currentYear + 5; year++) {
            for (int month = 1; month <= 12; month++) {
                YearMonth ym = YearMonth.of(year, month);
                LocalDate start = ym.atDay(1);
                LocalDate end = ym.atEndOfMonth();

                TimePeriod tp = new TimePeriod();
                tp.setYear(String.valueOf(year));
                tp.setMonth(ym.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)); // Returns "January"
                tp.setStartDate(start);
                tp.setEndDate(end);
                timePeriodRepository.save(tp);
            }
        }

		return "TimePeriod Added Successfuly";
	}
	
	public List<TimePeriod> timePeriod(){
		return timePeriodRepository.findAll();
	}
}
