package ua.in.sz.pattern.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class MyAspect {

	@Before("execution(* ua.in.sz.pattern.aspect.*Service.*(..))")
	public void before(JoinPoint joinpoint) {
		log.info("Before: {}", joinpoint.toShortString());
	}
}
