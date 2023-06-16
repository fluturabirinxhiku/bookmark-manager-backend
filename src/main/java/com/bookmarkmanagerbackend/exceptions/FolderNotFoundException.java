package com.bookmarkmanagerbackend.exceptions;

public class FolderNotFoundException extends RuntimeException {
    public FolderNotFoundException(Integer id) {
        super("Folder with id: " + id + " not found.");
    }
}
