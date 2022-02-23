package com.example.storeweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
	/**
	 * 지정된 이메일에 해당하는 회원정보를 반환한다.
	 * select * from members where email = ?
	 * @param email
	 * @return 회원정보, 해당하는 회원정보가 존재하지 않으면 Optional 객체는 비어있다.
	 */
	Optional<Member> findByEmail(String email);
}
