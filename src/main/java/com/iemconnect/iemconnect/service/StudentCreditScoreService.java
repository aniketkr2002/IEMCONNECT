package com.iemconnect.iemconnect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemconnect.iemconnect.dao.CreditScoreDao;
import com.iemconnect.iemconnect.dao.StudentCreditScoreServiceDao;
import com.iemconnect.iemconnect.dao.StudentDao;
import com.iemconnect.iemconnect.model.CreditScore;
import com.iemconnect.iemconnect.model.StudentCreditScore;
import com.iemconnect.iemconnect.model.StudentEntity;

@Service
public class StudentCreditScoreService {
	private static final double PAYMENT_HISTORY_WEIGHT = 0.4;
    private static final double BORROWING_WEIGHT = 0.2;
    private static final double ACTIVITY_LEVEL_WEIGHT = 0.2;
    private static final double PEER_FEEDBACK_WEIGHT = 0.2;
    private static final double MAX_SCORE = 5.0;
    
    
    
    @Autowired
    private StudentDao studentRepository;
    

    @Autowired
    private StudentCreditScoreServiceDao creditScoreRepository;
    
    @Autowired
    private CreditScoreDao creditScoreDao;

    public String addCreditScore(StudentCreditScore score) {
        Optional<StudentEntity> studentOpt = studentRepository.findByUserName(score.getUserName());
        if (studentOpt.isPresent()) {
            score.setStudent(studentOpt.get());
            creditScoreRepository.save(score);
            Optional<CreditScore> scsOpt = creditScoreDao.findByUserName(score.getUserName());
            CreditScore scs;
            
            if (scsOpt.isEmpty()) {
                scs = new CreditScore();
                scs.setUserName(score.getUserName());
                scs.setScs(calculateCreditScore(score));
                creditScoreDao.save(scs); 
            } else {
                scs = scsOpt.get();
                double currentScore = scs.getScs();
                double newScore =   (calculateCreditScore(score));
                double averageScore = (currentScore + newScore) / 2;
                scs.setScs((Math.min(averageScore, MAX_SCORE)));
                creditScoreDao.save(scs); 
            }
            
            return "saved successfully";
        }
        return "student not found";
    }

    private double calculateCreditScore(StudentCreditScore score) {
        double paymentHistory = (score.getPaymentHistoryScore());
        double borrowing = (score.getBorrowingScore());
        double activityLevel =(score.getActivityLevelScore());
        double peerFeedback = (score.getPeerFeedbackScore());

        double weightedPaymentHistory = paymentHistory * PAYMENT_HISTORY_WEIGHT;
        double weightedBorrowing = borrowing * BORROWING_WEIGHT;
        double weightedActivityLevel = activityLevel * ACTIVITY_LEVEL_WEIGHT;
        double weightedPeerFeedback = peerFeedback * PEER_FEEDBACK_WEIGHT;

        double totalScore = weightedPaymentHistory + weightedBorrowing + weightedActivityLevel + weightedPeerFeedback;
        return (Math.min(totalScore, MAX_SCORE));
    }

    public Optional<CreditScore> getCreditScoreByUserName(String userName) {
        return creditScoreDao.findByUserName(userName);
    }
}
