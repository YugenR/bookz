package com.vinai.bookz.controllers;

import com.vinai.bookz.services.UserBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
@Slf4j
@CrossOrigin(origins = "*")
public class UserBookController {

    @Autowired
    private UserBookService userBookService;


    @PutMapping("users/{uId}/books/{isbn}")
    public Integer readBook(@PathVariable Long uId, @PathVariable String isbn) {
        return userBookService.readThisBook(uId, isbn);
    }

}
