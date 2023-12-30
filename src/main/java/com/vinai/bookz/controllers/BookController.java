package com.vinai.bookz.controllers;

import com.vinai.bookz.dtos.BookDTO;
import com.vinai.bookz.dtos.PageConverterDTO;
import com.vinai.bookz.exceptions.NotFoundException.BookNotFound;
import com.vinai.bookz.services.BookService;
import jakarta.validation.Valid;
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
    public PageConverterDTO<BookDTO.BookData> getAllBooks(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int num,
            @RequestParam(required = false) List<String> sort,
            @RequestParam(required = false, defaultValue = "") String keyword
    ) {
        return bookService.getAllBooks(page, num, sort, keyword);
    }

    @GetMapping("{isbn}")
    public BookDTO.BookDetail getBook(@PathVariable String isbn) throws BookNotFound {
        return bookService.getBook(isbn);
    }

    @GetMapping("{isbn}/check")
    public BookDTO.IsbnCheckResponse checkIsbnAvailability(@PathVariable String isbn) throws BookNotFound {
        return bookService.checkIsbnAvailability(isbn);
    }

    @PostMapping("")
    public BookDTO.BookDetail createBook(@RequestBody @Valid BookDTO.BookCreate book) {
        return bookService.createBook(book);
    }

    @PatchMapping("{isbn}")
    public BookDTO.BookDetail updateBook(@PathVariable String isbn, @RequestBody @Valid BookDTO.BookUpdate book) {
        return bookService.updateBook(isbn, book);
    }

    @DeleteMapping("{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }

}
