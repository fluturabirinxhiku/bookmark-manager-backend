package com.bookmarkmanagerbackend.controllers;

import com.bookmarkmanagerbackend.models.Folder;
import com.bookmarkmanagerbackend.services.FolderService;
import com.bookmarkmanagerbackend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {
    private final FolderService folderService;
    private final UserService userService;

    public FolderController(FolderService folderService, UserService userService) {
        this.folderService = folderService;
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Folder> getUserFolders(Authentication auth) {
        Jwt currentJwt = (Jwt) auth.getPrincipal();
        return folderService.findAllByUser(currentJwt.getSubject());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Folder getFolderById(@PathVariable Integer id) {
        return folderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Folder createFolder(@RequestBody Folder folder, Authentication auth) {
        Jwt currentJwt = (Jwt) auth.getPrincipal();
        folder.setUser(userService.findByUsername(currentJwt.getSubject()));
        return folderService.save(folder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFolderById(@PathVariable Integer id) {
        folderService.deleteById(id);
    }

    @GetMapping("/check-folder/{folder}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkForFolder(@PathVariable String folder) {
        return folderService.existsByFolderName(folder);
    }

}
