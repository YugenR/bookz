package com.vinai.bookz.services;

import com.vinai.bookz.dtos.BookDTO;
import com.vinai.bookz.entities.Book;
import com.vinai.bookz.exceptions.NotFoundException.BookNotFound;
import com.vinai.bookz.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO.BookData> getAllBooks() {
        return bookRepository.findAll()
                .stream().map(Book::toDTOData).toList();
    }

    public BookDTO.BookDetail getBook(Long id) {
        return findBook(id).toDTODetail();
    }

    public BookDTO.BookDetail createBook(BookDTO.BookCreate bookDto) {
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getIsbn(), bookDto.getPlot());
        return bookRepository.save(book).toDTODetail();
    }

    public BookDTO.BookDetail updateBook(Long id, BookDTO.BookCreate bookDto) {
        Book book = findBook(id);
        book.update(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getIsbn(), bookDto.getPlot());
        return bookRepository.save(book).toDTODetail();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }


    public Book findBook(Long id) throws BookNotFound {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFound::new);
    }


}
