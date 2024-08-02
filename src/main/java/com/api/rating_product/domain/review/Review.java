package com.api.rating_product.domain.review;

import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.user.User;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

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
    @Column(nullable = false)
    private Double rating;
    @Column(nullable = false)
    private String comment;

    public Review (){

    }

    public Review (ReviewDTO data){

        this.rating = data.rating();
        this.comment = data.comment();

    }

    public Review(ReviewDTO data, Product product, User user) {
        this.rating = data.rating();
        this.comment = data.comment();
        this.productId = product;         // construtor para utilizar no teste
        this.userId = user;
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

    @Override  // comparação necessaria para teste unitario
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(productId, review.productId) &&
                Objects.equals(userId, review.userId) &&
                Objects.equals(rating, review.rating) &&
                Objects.equals(comment, review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, userId, rating, comment);
    }
}
