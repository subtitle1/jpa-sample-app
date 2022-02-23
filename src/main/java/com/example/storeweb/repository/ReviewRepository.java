package com.example.storeweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.storeweb.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
