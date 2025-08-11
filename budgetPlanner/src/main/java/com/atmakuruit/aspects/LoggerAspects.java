package com.atmakuruit.aspects;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspects {

	private Logger logger = Logger.getLogger(LoggerAspects.class.getName());
	
	@Around("execution(* com.atmakuruit.budgetPlanner.*.*.*(..))")
	public Object log(ProceedingJoinPoint jointPoint) throws Throwable {
		logger.info(jointPoint.getSignature().toString() + "method execution start");
		Instant start = Instant.now();
		Object result = jointPoint.proceed();
		Instant end = Instant.now();
		long timeElapsed = Duration.between(start, end).toMillis();
		logger.info("Time took to execute this method: "+ timeElapsed +" ms");
		logger.info(jointPoint.getSignature().toString() + "method execution end");
		return result;
	}
}
