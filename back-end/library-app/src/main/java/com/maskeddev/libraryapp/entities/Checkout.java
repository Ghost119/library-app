package com.maskeddev.libraryapp.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checkout")
@Data
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "checkout_date")
    @CreationTimestamp
    private Date checkoutDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "book_id")
    private Long bookId;
}
