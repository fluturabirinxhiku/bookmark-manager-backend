package com.bookmarkmanagerbackend.controllers;

import com.bookmarkmanagerbackend.models.Bookmark;
import com.bookmarkmanagerbackend.services.BookmarkService;
import com.bookmarkmanagerbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final UserService userService;

    public BookmarkController(BookmarkService bookmarkService, UserService userService) {
        this.bookmarkService = bookmarkService;
        this.userService = userService;
    }

    @GetMapping("/bookmarks")
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> getUserBookmarks(Authentication auth) {
        Jwt currentJwt = (Jwt) auth.getPrincipal();
        return bookmarkService.findAllByUser(currentJwt.getSubject());
    }

    @GetMapping("/folders/{folderName}/bookmarks")
    @ResponseStatus(HttpStatus.OK)
    public List<Bookmark> getAllBookmarksByUserAndFolder(Authentication auth, @PathVariable String folderName) {
        Jwt currentJwt = (Jwt) auth.getPrincipal();
        return bookmarkService.findAllByUserAndFolder(currentJwt.getSubject(), folderName);
    }

    @GetMapping("/bookmarks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bookmark getBookmarkById(@PathVariable Integer id) {
        return bookmarkService.findById(id);
    }

    @PostMapping("/bookmarks")
    @ResponseStatus(HttpStatus.CREATED)
    public Bookmark createBookmark(@RequestBody Bookmark bookmark, Authentication auth) {
        Jwt currentJwt = (Jwt) auth.getPrincipal();
        bookmark.setUser(userService.findByUsername(currentJwt.getSubject()));
        return bookmarkService.save(bookmark);
    }

    @DeleteMapping("/bookmarks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookmarkById(@PathVariable Integer id) {
        bookmarkService.deleteById(id);
    }
}
