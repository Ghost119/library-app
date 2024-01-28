package com.maskeddev.libraryapp.dao;

import com.maskeddev.libraryapp.entities.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
    Checkout findByUserEmailAndBookId(String userEmail, Long bookId);

    List<Checkout> findCheckoutsByUserEmail(String userEmail);
}
