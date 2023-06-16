package com.bookmarkmanagerbackend.controllers;

import com.bookmarkmanagerbackend.models.Bookmark;
import com.bookmarkmanagerbackend.models.User;
import com.bookmarkmanagerbackend.services.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> getUserBookmarks(Authentication auth){
        String username = auth.getName();
        return bookmarkService.findAllByUserUsername(username);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bookmark getBookmarkById(@PathVariable Integer id) {
        return bookmarkService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkService.save(bookmark);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookmarkById(@PathVariable Integer id) {
        bookmarkService.deleteById(id);
    }
}
