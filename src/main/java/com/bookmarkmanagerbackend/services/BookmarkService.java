package com.bookmarkmanagerbackend.services;

import com.bookmarkmanagerbackend.exceptions.BookmarkNotFoundException;
import com.bookmarkmanagerbackend.models.Bookmark;
import com.bookmarkmanagerbackend.repositories.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark findById(Integer id) {
        return bookmarkRepository.findById(id).orElseThrow(() -> new BookmarkNotFoundException(id));
    }

    public Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public void deleteById(Integer id) {
        bookmarkRepository.deleteById(id);
    }

    public List<Bookmark> findAllByUser(String username) {
        return bookmarkRepository.findAllByUser(username);
    }

    public List<Bookmark> findAllByUserAndFolder(String username, String folder) {
        return bookmarkRepository.findAllByUserAndFolder(username, folder);
    }
}
