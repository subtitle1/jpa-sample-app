package com.example.storeweb.web.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.storeweb.constant.Role;
import com.example.storeweb.entity.Member;
import com.example.storeweb.exception.StoreException;
import com.example.storeweb.service.MemberService;
import com.example.storeweb.web.form.MemberRegisterForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
	
    @GetMapping("/")
    public String home() {
    	log.info("홈 페이지 조회");
        return "home";
    }

    // 회원가입 화면 요청을 처리한다.
    @GetMapping("/register")
    public String registerForm(Model model) {
    	log.info("회원가입 페이지 조회");
    	// 폼입력값 유효성 체크를 위해서 폼입력값을 저장할 폼객체를 미리 생성해서 Model에 저장하고 registerForm.jsp로 전달한다.
    	model.addAttribute("memberRegisterForm", new MemberRegisterForm());
        return "registerForm";
    }

    // 회원가입 요청을 처리한다.
    // @Valid 어노테이션을 MemberRegisterForm 객체에 저장된 폼입력값에 대한 유효성체크를 수행시킨다.
    // BindingResult errors는 폼입력값 유효성 체크를 수행했을 때 발생한 오류 정보가 포함된 객체를 전달받는다.
    @PostMapping("/register")
    public String register(@ModelAttribute(name = "memberRegisterForm") @Valid MemberRegisterForm form, BindingResult errors) {
    	log.info("회원 가입하기");
    	
    	// 폼입력값 유효성 체크를 통과했는지 체크한다., errors.hasErrors()가 true를 반환하면 폼 입력값 유효성 체크를 통과하지 못한 것이다.
    	if (errors.hasErrors()) {
    		log.warn("폼입력값 유효성 오류 발생");
    		return "registerForm";
    	}
    	
    	
    	try {
    		// MemberRegisterForm객체에 저장된 값으로 Member객체를 생성한다.
    		Member member = Member.builder()
    				.email(form.getEmail())
    				.password(passwordEncoder.encode(form.getPassword()))
    				.name(form.getName())
    				.tel(form.getTel())
    				.role(Role.valueOf(form.getRole()))
    				.enabled(true).build();
    		// 서비스의 saveMember() 메소드를 호출해서 회원정보를 저장시킨다.
    		memberService.saveMember(member);
    		// 회원가입이 완료되면 회원가입 완료 요청을 재요청URI 응답으로 보낸다.
    		return "redirect:/completed";
    		
    	} catch (StoreException e) {
    		// 이메일 중복이 발생하는 것도 폼 입력값 유효성을 통과하지 못한 것으로 간주해서 BindResult 객체에 유효성 체크 오류 정보를 저장한다.
    		log.warn("이메일 중복 오류 발생");
    		// BindingResult의 rejectValue(필드명, 에러코드, 에러메세지) 메소드는 유효성 체크 실패정보를 BindResult에 추가한다.
    		errors.rejectValue("email", null, e.getMessage());
    		return "registerForm";
    	}
    }

    // 회원가입 완료 페이지 요청을 처리한다.
    @GetMapping("/completed")
    public String registerCompleted() {
    	log.info("회원가입 완료 페이지 요청");
        return "completed";
    }

    // 로그인 페이지 요청을 처리한다.
    @GetMapping("/login")
    public String loginForm() {
    	log.info("로그인 폼 페이지 요청");
        return "loginForm";
    }
}
