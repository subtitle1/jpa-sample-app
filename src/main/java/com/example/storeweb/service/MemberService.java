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
	
	public void saveMember(Member member) {
		Optional<Member> optional = memberRepository.findByEmail(member.getEmail());
		if (optional.isPresent()) {
			throw new AlreadyUsedEmailException("이미 사용중인 이메일입니다.");
		}
		memberRepository.save(member);
	}
}
