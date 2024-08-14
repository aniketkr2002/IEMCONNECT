package com.iemconnect.iemconnect.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 20, message = "Name should be at least two characters and unique")
    private String userName;

    @Column(nullable = false)
    @Size(min = 2, max = 20, message = "Name should be at least two characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format/College email required")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Current year is required")
    @Column(nullable = false)
    private String curryear;

    @NotBlank(message = "Current semester is required")
    @Column(nullable = false)
    private String semester;

    @Temporal(TemporalType.TIME)
    @Column
    private Date createdAt;

    @NotBlank(message = "Creator name is required")
    @Column(nullable = false)
    private String createdBy;

    @Temporal(TemporalType.TIME)
    @Column
    private Date lastUpdatedAt;

    @Column
    private String lastUpdatedBy;

    @NotBlank(message = "Phone is required")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Enrollment is required")
    @Column(nullable = false, unique = true)
    private String enrollment;

    @NotBlank(message = "Branch is required")
    @Column(nullable = false)
    private String branch;

    @NotBlank(message = "Password is required")
    @Size(min = 5)
    @Column(nullable = false)
    private String password;

    // New field for role
    @Column(nullable = false)
    private String role = "USER"; // Default role set to USER

    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurryear() {
        return curryear;
    }

    public void setCurryear(String curryear) {
        this.curryear = curryear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "StudentEntity {" +
                "studentId=" + studentId +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", curryear='" + curryear + '\'' +
                ", semester='" + semester + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", phone='" + phone + '\'' +
                ", enrollment='" + enrollment + '\'' +
                ", branch='" + branch + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
