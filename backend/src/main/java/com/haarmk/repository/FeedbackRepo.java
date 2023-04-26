package com.haarmk.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.haarmk.dto.FeedbackResDto;
import com.haarmk.model.Feedback;


public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{
	
	@Query("select new com.haarmk.dto.FeedbackResDto(f.id ,f.comment, f.rating, f.createdAt, new com.haarmk.dto.UserFeebackResDto(f.user.username, f.user.firstName, f.user.lastName,f.user.image) )from Feedback f")
    public List<FeedbackResDto> getAllfeedback();
	
	@Query("select new com.haarmk.dto.FeedbackResDto(f.id ,f.comment, f.rating, f.createdAt, new com.haarmk.dto.UserFeebackResDto(f.user.username, f.user.firstName, f.user.lastName,f.user.image) )from Feedback f where f.id =:id")
    public Optional<FeedbackResDto> getFeedbackById(@Param(value = "id") Integer id);
	
	
}
