package com.vinai.bookz.controllers;

import com.vinai.bookz.dtos.UserDTO;
import com.vinai.bookz.exceptions.NotFoundException.UserNotFound;
import com.vinai.bookz.services.UserBookService;
import com.vinai.bookz.services.UserService;
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


   @PutMapping("users/{uId}/books/{bId}")
   public Integer readBook(@PathVariable Long uId, @PathVariable Long bId) {
       return userBookService.readThisBook(uId, bId);
   }

}
