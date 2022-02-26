package com.example.storeweb.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.storeweb.security.User;

/**
 * 인증된 사용자의 정보를 제공하는 유틸리티 클래스다.
 * @author lee_e
 *
 */
public class SecurityUtils {

	/**
	 * 인증된 사용자의 회원아이디를 반환한다.
	 * @return 사용자 아이디
	 */
	public static long getMemberId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getId();
	}
}
