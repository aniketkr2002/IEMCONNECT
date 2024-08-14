package com.iemconnect.studentlog.model;

import jakarta.persistence.*;

@Entity
public class CreditScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double scs;
    
    private String userName;
    
    @OneToOne(fetch = FetchType.EAGER)
    private StudentEntity student;

    public CreditScore() {
    }

    public CreditScore(Double scs) {
        this.scs = scs;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getScs() {
        return scs;
    }

    public void setScs(double scs) {
        this.scs = scs;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "CreditScore{" +
                "id=" + id +
                ", scs='" + scs + '\'' +
                ", student=" + student +
                '}';
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
