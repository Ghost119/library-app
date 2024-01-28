package com.maskeddev.libraryapp.service;

import com.maskeddev.libraryapp.dao.BookRepository;
import com.maskeddev.libraryapp.dao.CheckoutRepository;
import com.maskeddev.libraryapp.entities.Book;
import com.maskeddev.libraryapp.entities.Checkout;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class BookService {
    private BookRepository bookRepository;

    private CheckoutRepository checkoutRepository;

    public BookService(BookRepository bookRepository, CheckoutRepository checkoutRepository){
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public Book checkoutBook(String userEmail, Long bookId) throws Exception {
        Optional<Book> book = bookRepository.findById(bookId);

        boolean isBookCheckedout = isBookCheckedoutByUser(userEmail, bookId);

        if(!book.isPresent() || isBookCheckedout || book.get().getCopiesAvailable() <= 0){
            throw new Exception("Book doesn't exist or already checked out by the user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                bookId);
        checkoutRepository.save(checkout);

        return book.get();
    }

    public boolean isBookCheckedoutByUser(String userEmail, Long bookId){
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
        return null != validateCheckout;
    }

    public int getCheckoutCount(String userEmail){
        return checkoutRepository.findCheckoutsByUserEmail(userEmail).size();
    }
}
