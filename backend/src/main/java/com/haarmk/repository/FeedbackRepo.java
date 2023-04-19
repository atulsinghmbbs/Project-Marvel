package com.haarmk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.dto.FeedbackDto;
import com.haarmk.model.Feedback;


public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{
	
	@Query("select new com.haarmk.dto.FeedbackDto(f.id , f.user.firstName, f.user.lastName)from Feedback f")
    public List<FeedbackDto> GetAllfeedback();
}
