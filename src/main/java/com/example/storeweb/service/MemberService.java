package com.example.storeweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.storeweb.entity.Member;
import com.example.storeweb.exception.AlreadyUsedEmailException;
import com.example.storeweb.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	/**
	 * 회원정보를 전달받아서 회원가입 시킨다.
	 * @param member 회원정보
	 */
	public void saveMember(Member member) {
		// 이메일로 회원정보를 조회해서 동일한 이메일을 사용하는 회원이 있는지 조회해서 회원이 존재하면 예외를 발생시킨다.
		Optional<Member> optional = memberRepository.findByEmail(member.getEmail());
		if (optional.isPresent()) {	// Optional객체의 isPresent() 메소드는 Option객체에 Member객체가 존재하면 true를 반환한다.
			throw new AlreadyUsedEmailException("이미 사용중인 이메일입니다.");
		}
		// 동일한 이메일을 가진 회원이 없으면 회원정보를 저장시킨다.
		memberRepository.save(member);
	}
}
