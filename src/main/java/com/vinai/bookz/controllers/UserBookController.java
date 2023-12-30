package com.vinai.bookz.controllers;

import com.vinai.bookz.dtos.BookDTO;
import com.vinai.bookz.dtos.PageConverterDTO;
import com.vinai.bookz.services.UserBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("")
@Slf4j
@CrossOrigin(origins = "*")
public class UserBookController {

    @Autowired
    private UserBookService userBookService;


    @PutMapping("users/{uId}/books/{isbn}/add")
    public void addBookToLibrary(@PathVariable Long uId, @PathVariable String isbn) {
        userBookService.addToLibrary(uId, isbn);
    }

    @PutMapping("users/{uId}/books/{isbn}")
    public Integer readThisBook(@PathVariable Long uId, @PathVariable String isbn) {
        return userBookService.readThisBook(uId, isbn);
    }

    @DeleteMapping("users/{uId}/books/{isbn}")
    public void removeBookFromLibrary(@PathVariable Long uId, @PathVariable String isbn) {
        userBookService.removeBookFromLibrary(uId, isbn);
    }

    @GetMapping("users/{uId}/books")
    public PageConverterDTO<BookDTO.BookData> getMyBooks(@RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "20") int num,
                                                           @RequestParam(required = false) List<String> sort,
                                                           @RequestParam(required = false, defaultValue = "") String keyword, @PathVariable Long uId) {
        return userBookService.getMyBooks(uId, page, num, sort, keyword);
    }
}
