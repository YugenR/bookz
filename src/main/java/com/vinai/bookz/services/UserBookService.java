package com.vinai.bookz.services;

import com.vinai.bookz.entities.Book;
import com.vinai.bookz.entities.User;
import com.vinai.bookz.entities.UserBook;
import com.vinai.bookz.repositories.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserBookService {

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public Integer readThisBook(Long userId, String isbn) {
        User user = userService.findUser(userId);
        Book book = bookService.findBook(isbn);
        UserBook userBook;

        Optional<UserBook> userBookOptional = userBookRepository.findById(new UserBook.UserBookId(user, book));
        if (userBookOptional.isPresent()) {
            userBook = userBookOptional.get();
            userBook.setTimes(userBook.getTimes() + 1);
        } else {
            userBook = new UserBook(user, book);
        }
        userBookRepository.save(userBook);
        return userBook.getTimes();

    }


}
