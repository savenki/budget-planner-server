package com.atmakuruit.aspects;

import java.time.Duration;
import java.time.Instant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAspects {

	
	@Around("execution(* com.atmakuruit.budgetPlanner.*.*.*(..))")
	public Object logger(ProceedingJoinPoint jointPoint) throws Throwable {
		log.info(jointPoint.getSignature().toString() + "method execution start");
		Instant start = Instant.now();
		Object result = jointPoint.proceed();
		Instant end = Instant.now();
		long timeElapsed = Duration.between(start, end).toMillis();
		log.info("Time took to execute this method: "+ timeElapsed +" ms");
		log.info(jointPoint.getSignature().toString() + "method execution end");
		return result;
	}
}
