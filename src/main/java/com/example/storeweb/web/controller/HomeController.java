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
        return "/home";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
    	log.info("회원가입 페이지 조회");
    	
    	model.addAttribute("memberRegisterForm", new MemberRegisterForm());
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(name = "memberRegisterForm") @Valid MemberRegisterForm form, BindingResult errors) {
    	log.info("회원 가입하기");
    	if (errors.hasErrors()) {
    		log.warn("폼입력값 유효성 오류 발생");
    		return "registerForm";
    	}
    	try {
    		Member member = Member.builder()
    				.email(form.getEmail())
    				.password(passwordEncoder.encode(form.getPassword()))
    				.name(form.getName())
    				.tel(form.getTel())
    				.role(Role.valueOf(form.getRole()))
    				.enabled(true).build();
    		memberService.saveMember(member);
    	} catch (StoreException e) {
    		log.warn("아이디 중복 오류 발생");
    		errors.rejectValue("email", null, e.getMessage());
    		return "registerForm";
    	}
        return "redirect:/completed";
    }

    @GetMapping("/completed")
    public String registerCompleted() {
    	log.info("회원가입 완료 페이지 요청");
        return "completed";
    }

    @GetMapping("/login")
    public String loginForm() {
    	log.info("로그인 폼 페이지 요청");
        return "loginForm";
    }
}
