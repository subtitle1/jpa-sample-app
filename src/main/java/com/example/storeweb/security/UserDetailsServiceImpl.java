package com.example.storeweb.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.storeweb.constant.Role;
import com.example.storeweb.entity.Member;
import com.example.storeweb.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	// 인증에 필요한 사용자정보를 조회해서 UserDetails 인터페이스 구현객체(이 애플리케이션에서는 User객체다)로 반환한 메소드
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Member> optional = memberRepository.findByEmail(email);
		Member member = optional.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
		
		return new User(member, this.getAuthorities(member.getRole()));
	}
	
	// 사용자가 보유한 권한정보로 SimpleGrantedAuthority객체를 생성해서 반환하는 메소드다.
	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

}
