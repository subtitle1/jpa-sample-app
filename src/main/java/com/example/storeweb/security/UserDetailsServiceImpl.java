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
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Member> optional = memberRepository.findByEmail(email);
		Member member = optional.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
		
		return new User(member, this.getAuthorities(member.getRole()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

}
