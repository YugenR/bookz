package com.vinai.bookz.services;

import com.vinai.bookz.common.pagination.PageConverter;
import com.vinai.bookz.common.pagination.SortableEntities;
import com.vinai.bookz.common.pagination.SortableFields;
import com.vinai.bookz.dtos.BookDTO;
import com.vinai.bookz.dtos.PageConverterDTO;
import com.vinai.bookz.entities.Book;
import com.vinai.bookz.entities.User;
import com.vinai.bookz.entities.UserBook;
import com.vinai.bookz.exceptions.NotFoundException;
import com.vinai.bookz.repositories.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
            // todo Book not in library
            userBook = new UserBook(user, book, 0);
        }
        userBookRepository.save(userBook);
        return userBook.getTimes();

    }

    public void addToLibrary(Long userId, String isbn) {
        User user = userService.findUser(userId);
        Book book = bookService.findBook(isbn);
        userBookRepository.save(new UserBook(user, book, 0));
        ;
    }

    public void removeBookFromLibrary(Long userId, String isbn) {
        User user = userService.findUser(userId);
        Book book = bookService.findBook(isbn);
        UserBook ub = userBookRepository.findById(new UserBook.UserBookId(user, book))
                .orElseThrow(NotFoundException.BookNotInLibrary::new);
        userBookRepository.delete(ub);
    }


    public PageConverterDTO<BookDTO.BookData> getMyBooks(Long userId,
                                                           Integer page,
                                                           Integer num,
                                                           List<String> sort,
                                                           String keyword) {
        return PageConverter
                .toDTOPage(userBookRepository.findAllByUser(
                        (page == null && num == null) ? null : PageRequest.of(
                                page != null ? page : SortableFields.DEFAULT_PAGE,
                                num != null ? num : SortableFields.DEFAULT_PAGE_DIM,
                                SortableFields.getSorter(SortableEntities.USERBOOKS, sort)
                        ), userId, keyword
                ).map(userBook -> userBook.getBook().toDTOData()));


    }
}
