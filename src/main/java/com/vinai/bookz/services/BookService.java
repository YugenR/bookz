package com.vinai.bookz.services;

import com.vinai.bookz.common.pagination.PageConverter;
import com.vinai.bookz.dtos.PageConverterDTO;
import com.vinai.bookz.common.pagination.SortableEntities;
import com.vinai.bookz.common.pagination.SortableFields;
import com.vinai.bookz.dtos.BookDTO;
import com.vinai.bookz.entities.Book;
import com.vinai.bookz.exceptions.BadRequestException;
import com.vinai.bookz.exceptions.NotFoundException.BookNotFound;
import com.vinai.bookz.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * Gets all existing books
     */
    public PageConverterDTO<BookDTO.BookData> getAllBooks(
            Integer page,
            Integer num,
            List<String> sort,
            String keyword
    ) {
        return PageConverter
                .toDTOPage(bookRepository.findAll(
                        (page == null && num == null) ? null : PageRequest.of(
                                page != null ? page : SortableFields.DEFAULT_PAGE,
                                num != null ? num : SortableFields.DEFAULT_PAGE_DIM,
                                SortableFields.getSorter(SortableEntities.BOOKS, sort)
                        ), keyword
                ).map(Book::toDTOData));
    }

    public BookDTO.BookDetail getBook(String isbn) {
        return findBook(isbn).toDTODetail();
    }

    public BookDTO.IsbnCheckResponse checkIsbnAvailability(String isbn) {
        Optional<Book> optBook = bookRepository.findById(isbn);

        return new BookDTO.IsbnCheckResponse(optBook.isPresent(), optBook.isPresent() ? optBook.get().getTitle() : "");
    }

    public BookDTO.BookDetail createBook(BookDTO.BookCreate bookDto) {

        if (checkIsbnExists(bookDto.getIsbn()))
            throw new BadRequestException.IsbnAlreadyExists();

        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getIsbn(), bookDto.getPlot());
        return bookRepository.save(book).toDTODetail();
    }

    public BookDTO.BookDetail updateBook(String isbn, BookDTO.BookUpdate bookDto) {
        Book book = findBook(isbn);
        book.update(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPlot());
        return bookRepository.save(book).toDTODetail();
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }


    public Book findBook(String isbn) throws BookNotFound {
        return bookRepository.findById(isbn)
                .orElseThrow(BookNotFound::new);
    }

    public Boolean checkIsbnExists(String isbn) {
        return bookRepository.findById(isbn).isPresent();
    }


}
