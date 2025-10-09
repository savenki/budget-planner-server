package com.atmakuruit.budgetPlanner;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages={"com.atmakuruit.budgetPlanner", "com.atmakuruit.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfig {

}
