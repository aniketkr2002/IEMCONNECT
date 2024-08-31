package com.iemconnect.iemconnect.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.iemconnect.iemconnect.model.Comment;
import com.iemconnect.iemconnect.model.CreditScore;
import com.iemconnect.iemconnect.model.Post;
import com.iemconnect.iemconnect.model.Product;
import com.iemconnect.iemconnect.model.StudentCreditScore;
import com.iemconnect.iemconnect.service.PostService;
import com.iemconnect.iemconnect.service.ProductService;
import com.iemconnect.iemconnect.service.StudentCreditScoreService;
import com.iemconnect.iemconnect.util.Message;


@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StudentCreditScoreService studentCreditScoreService;

    // POST APIs for Posts

    @PostMapping("/posts")
    public ResponseEntity<String> addPost(
            @RequestParam("userName") String userName,
            @RequestParam("postName") String postName,
            @RequestParam("description") String description,
            @RequestParam("price") String price,
            @RequestParam("likesCount") String likesCount,
            @RequestParam("dislikesCount") String dislikesCount,
            @RequestParam("image") MultipartFile image) {

        byte[] imageBytes;
        try {
            imageBytes = image.getBytes();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to convert image");
        }

        Post post = new Post(
                userName,
                postName,
                description,
                price,
                imageBytes,
                likesCount,
                dislikesCount
        );

        postService.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post saved successfully");
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/user/{userName}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable("userName") String userName) {
        List<Post> posts = postService.getPost(userName);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts/comments")
    public ResponseEntity<Comment> addComment(
            @RequestParam("postUserName") String postUserName,
            @RequestParam("content") String content,
            @RequestParam("commentUserName") String commentUserName) {

        Comment comment = new Comment(content, postUserName, commentUserName);
        Comment savedComment = postService.addComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @GetMapping("/posts/comments/{postUserName}")
    public ResponseEntity<List<Comment>> getPostComments(@PathVariable("postUserName") String postUserName) {
        List<Comment> comments = postService.getComment(postUserName);
        return ResponseEntity.ok(comments);
    }

    // POST APIs for Products

    @PostMapping("add/products")
    public ResponseEntity<String> addProduct(
            @RequestParam("userName") String userName,
            @RequestParam("contactNo") String contactNo,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") String price,
            @RequestParam("file") MultipartFile file) {

        byte[] imageBytes;
        try {
            imageBytes = file.getBytes();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to convert image");
        }

        Product product = new Product(
                userName,
                contactNo,
                productName,
                description,
                price,
                imageBytes
        );

        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/user/{userName}")
    public ResponseEntity<List<Product>> getUserProducts(@PathVariable("userName") String userName) {
        List<Product> products = productService.getMyProduct(userName);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<String> updateProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody Product newUpdate) {

        productService.updateMyProduct(newUpdate, productId);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) {
        productService.deleteMyProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // POST APIs for Student Credit Score

    @PostMapping("/rate")
    public ResponseEntity<String> addCreditScore(@RequestBody StudentCreditScore score) {
        String result = studentCreditScoreService.addCreditScore(score);

        if ("saved successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save credit score/Student Not Found");
        }
    }

    @GetMapping("/rate/{userName}")
    public ResponseEntity<Object> getCreditScore(@PathVariable String userName) {
        Optional<CreditScore> scoreOptional = studentCreditScoreService.getCreditScoreByUserName(userName);
        if (scoreOptional.isPresent()) {
            return ResponseEntity.ok(scoreOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
}
