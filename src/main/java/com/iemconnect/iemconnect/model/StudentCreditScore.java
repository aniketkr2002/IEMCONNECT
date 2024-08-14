package com.iemconnect.iemconnect.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentCreditScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName; 
    private Double paymentHistoryScore;
    private Double borrowingScore;
    private Double activityLevelScore;
    private Double peerFeedbackScore;

    @ManyToOne
    private StudentEntity student; 

    public StudentCreditScore() {
    }

    public StudentCreditScore(String userName, Double  paymentHistoryScore,Double borrowingScore, Double activityLevelScore, Double peerFeedbackScore) {
        this.userName = userName;
        this.paymentHistoryScore = paymentHistoryScore;
        this.borrowingScore = borrowingScore;
        this.activityLevelScore = activityLevelScore;
        this.peerFeedbackScore = peerFeedbackScore;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double  getPaymentHistoryScore() {
        return paymentHistoryScore;
    }

    public void setPaymentHistoryScore(Double  paymentHistoryScore) {
        this.paymentHistoryScore = paymentHistoryScore;
    }

    public Double  getBorrowingScore() {
        return borrowingScore;
    }

    public void setBorrowingScore(Double  borrowingScore) {
        this.borrowingScore = borrowingScore;
    }

    public Double  getActivityLevelScore() {
        return activityLevelScore;
    }

    public void setActivityLevelScore(Double activityLevelScore) {
        this.activityLevelScore = activityLevelScore;
    }

    public Double getPeerFeedbackScore() {
        return peerFeedbackScore;
    }

    public void setPeerFeedbackScore(Double peerFeedbackScore) {
        this.peerFeedbackScore = peerFeedbackScore;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentCreditScore{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", paymentHistoryScore=" + paymentHistoryScore +
                ", borrowingScore=" + borrowingScore +
                ", activityLevelScore=" + activityLevelScore +
                ", peerFeedbackScore=" + peerFeedbackScore +
                ", student=" + student +
                '}';
    }
}
