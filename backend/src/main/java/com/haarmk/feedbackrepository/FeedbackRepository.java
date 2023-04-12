package com.haarmk.feedbackrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haarmk.feedbackmodel.Feedback;
import com.haarmk.feedbackmodel.feedbackdto;

public interface FeedbackRepository extends JpaRepository<Feedback , Integer>  {

	 @Query("select new com.haarmk.feedbackmodel.feedbackdto(f.feedBackId , f.user.firstName, f.user.lastName)from Feedback f")
	 public List<feedbackdto> GetAllfeedback();
}
