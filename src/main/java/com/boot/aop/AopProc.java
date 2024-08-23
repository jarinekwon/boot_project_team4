package com.boot.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AopProc {
	//@Around("within(boot.com.controller.*)") //within 키워드는 패키지명을 포함한 클래스 경로를 나타낸다. 패키지명까지만 입력
	//@Around("execution(* com.boot.controller.MyPageController.*())")
	//@Around("execution(* com.boot.controller.MyPageController.*(..)) || execution(* com.boot.controller.RecruitController.*())") // 여러 개를 걸때
	@Around("execution(* com.boot.controller.MyPageController.*(..)) || execution(* com.boot.controller.RecruitController.*(..)) " 
			+ "|| execution(* com.boot.controller.BoardController.*(..)) || execution(* com.boot.controller.CommentController.*(..)) ")
			//+ "|| execution(* com.boot.controller.ResumeController.*(..)) || execution(* com.boot.controller.JobaplytbController.*(..))")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		log.info("@# loggerAop");
		//세션 아이디 체크(일반 && 기업)
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		if(request != null) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");// 현재 로그인한 id
			String usergubun = (String)session.getAttribute("usergubun");// 현재 로그인한 사용자 구분
			if(id == null && usergubun == null) {
				//세션에 id 변수가 없으면 로그인 페이지로 리다이렉트
	            //return "redirect:login"; // AOP에서는 반환값이 null일 경우 원본 메서드를 실행하지 않음
				HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('로그인이 필요합니다.\\n로그인 화면으로 이동합니다.'); location.href='/login';</script>"); 
                response.getWriter().flush();
                return null;
			}
		}
	    // proceed() 메서드를 호출하여 원본 메서드를 실행
	    return joinpoint.proceed();
	}
	/*
	@Around("execution(* com.boot.controller.RecruitController.*(..))")
	public Object loggerAop_company(ProceedingJoinPoint joinpoint) throws Throwable {
		log.info("@# loggerAop_company");
		//세션 아이디 체크(일반 && 기업)
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		if(request != null) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");// 현재 로그인한 id
			String usergubun = (String)session.getAttribute("usergubun");// 현재 로그인한 사용자 구분
			if((id == null && usergubun == null) || id != null && usergubun.equals("p")) { //기업회원만 가능한 기능
				//세션에 id 변수가 없으면 로그인 페이지로 리다이렉트
	            //return "redirect:login"; // AOP에서는 반환값이 null일 경우 원본 메서드를 실행하지 않음
				HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('해당 기능은 기업 회원의 기능입니다.'); location.href='/main';</script>");
                response.getWriter().flush();
                return null;
			}
		}
	    // proceed() 메서드를 호출하여 원본 메서드를 실행
	    return joinpoint.proceed();
	}
	*/
	
	/*	
	@Around("execution(* com.boot.controller.ScribeController.*(..)) ")
	public Object loggerAop_person(ProceedingJoinPoint joinpoint) throws Throwable {
		log.info("@# loggerAop_person");
		
		//세션 아이디 체크(일반 && 기업)
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();; 
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(request != null) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");// 현재 로그인한 id
			String usergubun = (String)session.getAttribute("usergubun");// 현재 로그인한 사용자 구분
			log.info("id :" + id);
			log.info("usergubun :" + usergubun);
			if( (id == null && usergubun == null) || (usergubun.equals("c")) ) { //일반 회원만 가능한 기능
				//세션에 id 변수가 없으면 로그인 페이지로 리다이렉트
	            //return "redirect:login"; // AOP에서는 반환값이 null일 경우 원본 메서드를 실행하지 않음
                //out.println("<script>alert('로그인이 필요합니다.'); location.href='/login';</script>");
                //out.flush();
                log.info("bb");
                return "redirect:login";
			}
		}
		log.info("aa");
	    // proceed() 메서드를 호출하여 원본 메서드를 실행
	    return joinpoint.proceed();
	}
	*/
}