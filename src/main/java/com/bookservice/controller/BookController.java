package com.bookservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bookservice.model.Book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import org.flywaydb.core.internal.configuration.models.EnvironmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("book-service")

public class BookController {
    
    @Autowired
    Environment environment;

    @GetMapping("/{id}/{currency}")
    public Book findBook(@RequestParam(value = "id", defaultValue = "1") Long id,
            @RequestParam(value = "currency", defaultValue = "USD") String currency){
                
            var port = environment.getProperty("local.server.port");

            // (Long id, String author, String title, Date launchDate, double price, String currency, String enviroment
        return new Book(1L, "Author", "Title", new Date(), Double.valueOf(12.7), currency, port);
    }

}
