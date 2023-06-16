package com.bookmarkmanagerbackend.repositories;

import com.bookmarkmanagerbackend.models.Bookmark;
import com.bookmarkmanagerbackend.models.Folder;
import com.bookmarkmanagerbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findAllByUserUsername(String username);
    List<Bookmark> findAllByUserUsernameAndFolderName(String username, String folder);
}
