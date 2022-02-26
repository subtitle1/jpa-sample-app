package com.example.storeweb.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.storeweb.entity.Member;

import lombok.ToString;

@ToString(of = {"id", "email", "name", "enabled", "authorities"})
public class User implements UserDetails {
	
	private static final long serialVersionUID = -8513640359886600514L;
	
	private Long id;												// 회원아이디, Member 엔티티의 id값이 저장된다.
	private String email;											// 이메일
	private String password;										// 암호화된 비밀번호
	private String name;											// 회원 이름
	private boolean enabled;										// 회원계정 활성화 여부
	private Collection<? extends GrantedAuthority> authorities;		// 회원이 보유하고 잇는 권한

	public User(Member member, Collection<? extends GrantedAuthority> authorities) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.name = member.getName();
		this.enabled = member.isEnabled();
		this.authorities = authorities;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


}
