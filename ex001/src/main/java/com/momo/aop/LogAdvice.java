package com.momo.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.momo.service.LogService;
import com.momo.vo.LogVO;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	
	/**
	 * AOP(Aspect-Oriented Programming)
	 * 	관점지향프로그램
	 * 	핵심비지니스 로직과 부가적인 관심사를 분리 하여 개발하는 방법론
	 * 
	 * 	코드의 중복을 줄이고 유지보수성을 향상 시킬수 있다
	 * 
	 * 부가적인 관심사
	 * 	로깅, 보안, 트랜젝션 관리등
	 * 	애플리케이션에서 공통적으로 처리해야 하는 기능
	 * 	오류발생시 데이터베이스에 저장
	 */
	
	/**
	 * 포인트 컷 : 언제 어디에 적용할 건지 기술
	 * 
	 * Before :
	 * 			타겟 객체의 메소드가 실행되기 전에 호추되는 어드바이스
	 * 			JoinPoint를 통해 파라미터 정보 참조 가능
	 */
	@Before("execution(* com.momo.service.Board*.*(..))")
	public void logBefore() {
		log.info("================AOP=================");

	}
	
	/**
	 * joinPoint
	 * 			타겟에 대한 정보와 상태를 담고 있는 객체로 매개변수로 받아서 사용
	 * @param joinPoint
	 */
	@Before("execution(* com.momo.service.Reply*.*(..))")
	public void logBeforeParams(JoinPoint joinPoint) {
		log.info("==============joinpointAOP===============");
		log.info("Param :"  + Arrays.toString(joinPoint.getArgs()));
		log.info("Target :" + joinPoint.getTarget());
		log.info("Method :" + joinPoint.getSignature().getName());
		
	}
	
	/**
	 * Around
	 * 		타겟의 메소드가 호출되기전 이전 시점과 이후 시점에 모두 처리해야
	 * 		할 필요가 있는 부가 기능 정의
	 * 
	 * 		주업무로직을 실행하기위해 JoinPoint의 하위 클래스인
	 * 		ProceedingJoinPoint타입의 파라미터를 필수적으로 선언해야함
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("execution(* com.momo.service.Board*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object res = "";
		// 주 업무로직 실행(타겟 메서드의 실생시점을 정할 수 있다.)
		try {
			res = pjp.proceed();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		stopWatch.stop();
		
		log.info(pjp.getTarget() +"."+ pjp.getSignature().getName());
		log.info("수행시간 : " +stopWatch.getTotalTimeMillis()+"(ms)초");
		
		return res;
	}
	
	@Autowired
	LogService logService;
	
	/**
	 * After Throwing
	 * 		타겟 메서드 실행중 예외가 발생한 뒤에 실행할 부가 기능
	 * 		오류가  발생내역을 테이블에 저장
	 * @param joinPoint
	 * @param exception
	 */
	@AfterThrowing(pointcut="execution(* com.momo.service.Reply*.*(..))", throwing="exception")
	public void logException(JoinPoint joinPoint, Exception exception) {
		//예외가 발생시 예외 내용을 테이블에 저장한다.
		
		try {
			LogVO vo = new LogVO();
			
			vo.setClassName(joinPoint.getTarget().getClass().getName());
			vo.setErrmsg(exception.getMessage());
			vo.setMethodName(joinPoint.getSignature().getName());
			//vo.setParams(Arrays.toString(joinPoint.getArgs()));
			Object[] args = joinPoint.getArgs();
			String params = Arrays.toString(args);
			if (params.getBytes().length > 1000) {
			    byte[] byteParams = params.getBytes();
			    byteParams = Arrays.copyOf(byteParams, 1000);
			    params = new String(byteParams);
			}
			vo.setParams(params);


			
			logService.insert(vo);
			log.info("========== 로그테이블 저장 성공 ==============");
			
		} catch (Exception e) {
			log.info("로그테이블 저장중 예외 발생");
			log.info(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
