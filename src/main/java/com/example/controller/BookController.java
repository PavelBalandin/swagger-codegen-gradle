package com.example.controller;

import com.example.api.BooksApi;
import com.example.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController implements BooksApi {

    private final Map<Integer, Book> books = new HashMap<>();
    int size = 0;

    @PostConstruct
    void init() {
        size++;
        Book book = new Book();
        book.setId(size);
        book.setName("Java 8");
        book.setPrice(300);
        book.setStatus(Book.StatusEnum.IN_STOCK);
        books.put(1, book);
    }

    @Override
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(new ArrayList<>(books.values()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> getById(Integer id) {
        return new ResponseEntity<>(books.get(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> update(Integer id, Book body) {
        Book book = books.get(id);
        book.setName(body.getName());
        book.setPrice(body.getPrice());
        book.setStatus(body.getStatus());

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> create(Book body) {
        size++;
        books.put(size, body);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> delete(Integer id) {
        books.remove(id);
        size--;
        return null;
    }
}


