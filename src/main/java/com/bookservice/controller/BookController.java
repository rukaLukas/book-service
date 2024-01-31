package com.bookservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bookservice.model.Book;
import com.bookservice.proxy.CambioProxy;
import com.bookservice.repository.BookRepository;
import com.bookservice.response.Cambio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;

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

    @Autowired
    private CambioProxy cambioProxy;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
        @PathVariable("id") Long id,
        @PathVariable("currency") String currency){
                
            var book = repository.getReferenceById(id);
            if (book == null) throw new RuntimeException("Book not found");

            var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);            

            var port = environment.getProperty("local.server.port");
            book.setEnviroment(port + " FEIGN");
            book.setPrice(cambio.getConvertedValue());

          
        return book;
    }

}
