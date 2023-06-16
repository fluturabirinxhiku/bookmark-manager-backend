package com.bookmarkmanagerbackend.controllers;

import com.bookmarkmanagerbackend.models.Folder;
import com.bookmarkmanagerbackend.services.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {
    private final FolderService bookmarkService;

    public FolderController(FolderService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Folder> getUserFolders(Authentication auth){
        String username = auth.getName();
        return bookmarkService.findAllByUserUsername(username);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Folder getFolderById(@PathVariable Integer id) {
        return bookmarkService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Folder createFolder(@RequestBody Folder bookmark) {
        return bookmarkService.save(bookmark);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFolderById(@PathVariable Integer id) {
        bookmarkService.deleteById(id);
    }

}
