package com.vinai.bookz.controllers;

import com.vinai.bookz.dtos.BookDTO;
import com.vinai.bookz.exceptions.NotFoundException.BookNotFound;
import com.vinai.bookz.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("books")
@Slf4j
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("")
    public List<BookDTO.BookData> getBooks() {
        return bookService.getAllBooks();

    }

    @GetMapping("{id}")
    public BookDTO.BookDetail getBook(@PathVariable long id) throws BookNotFound {
        return bookService.getBook(id);
    }

    @PostMapping("")
    public BookDTO.BookDetail createBook(@RequestBody BookDTO.BookCreate book) {
        return bookService.createBook(book);
    }

    @PatchMapping("{id}")
    public BookDTO.BookDetail updateBook(@PathVariable long id, @RequestBody BookDTO.BookCreate book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

}
