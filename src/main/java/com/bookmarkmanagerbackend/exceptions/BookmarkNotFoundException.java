package com.bookmarkmanagerbackend.exceptions;

public class BookmarkNotFoundException extends RuntimeException {
    public BookmarkNotFoundException(Integer id) {
        super("Bookmark with id: " + id + " not found.");
    }
}


