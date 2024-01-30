package com.bookservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Book;
import com.bookservice.repository.BookRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import org.flywaydb.core.internal.configuration.models.EnvironmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("book-service")
public class BookController {
    
    @Autowired
    BookRepository repository;

    @Autowired
    Environment environment;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
        @PathVariable("id") Long id,
        @PathVariable("currency") String currency){
                
            var book = repository.getReferenceById(id);
            if (book == null) throw new RuntimeException("Book not found");

            var port = environment.getProperty("local.server.port");
            book.setEnviroment(port);

          
        return book;
    }

}
