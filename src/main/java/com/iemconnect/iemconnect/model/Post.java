package com.iemconnect.iemconnect.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    @Column(nullable = false)
    private String postName;
    
    @Column(nullable = false)
    private String userName;

    @ManyToOne
    private StudentEntity student;
    
    @Column(nullable = false)
    private String description;

    @Lob
    private byte[] image;

    @Column(nullable = false)
    private String likesCount;

    @Column(nullable = false)
    private String dislikesCount;

    @Column(nullable = false)
    private String price;

    // Default constructor
    public Post() {}

    // Parameterized constructor
    public Post(String userName, String postName, String description, String price, byte[] imageBytes, String likesCount, String dislikesCount) {
        this.userName = userName;
        this.postName = postName;
        this.description = description;
        this.price = price;
        this.image = imageBytes;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(String dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
