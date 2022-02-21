package com.example.storeweb.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.storeweb.entity.Member;

import lombok.ToString;

@ToString(of = {"id", "email", "name", "tel", "enabled", "authorities"})
public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = -8513640359886600514L;
	
	private Long id;
	private String email;
	private String password;
	private String name;
	private String tel;
	private boolean enabled;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Member member, Collection<? extends GrantedAuthority> authorities) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.name = member.getName();
		this.tel = member.getTel();
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
	
	public String getTel() {
		return tel;
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
