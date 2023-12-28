package com.vinai.bookz.controllers;

import com.vinai.bookz.common.pagination.SortableEntities;
import com.vinai.bookz.dtos.BookDTO;
import com.vinai.bookz.dtos.PageConverterDTO;
import com.vinai.bookz.exceptions.NotFoundException.BookNotFound;
import com.vinai.bookz.services.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
    public PageConverterDTO<BookDTO.BookData> getBooks(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false) List<String> sort,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int num
    ) {
        return bookService.getAllBooks(page, num, sort, search);
    }

    @GetMapping("{id}")
    public BookDTO.BookDetail getBook(@PathVariable long id) throws BookNotFound {
        return bookService.getBook(id);
    }

    @PostMapping("")
    public BookDTO.BookDetail createBook(@RequestBody @Valid BookDTO.BookCreate book) {
        return bookService.createBook(book);
    }

    @PatchMapping("{id}")
    public BookDTO.BookDetail updateBook(@PathVariable long id, @RequestBody @Valid BookDTO.BookUpdate book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

}
