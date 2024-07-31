package com.api.rating_product.domain.review;

import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.user.User;
import jakarta.persistence.*;

@Entity(name = "reviews")
@Table(name = "tb_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product productId;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;
    private Double rating;
    private String comment;

    public Review (){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
