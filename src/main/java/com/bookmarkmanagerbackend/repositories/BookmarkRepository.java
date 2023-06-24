package com.bookmarkmanagerbackend.repositories;

import com.bookmarkmanagerbackend.models.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    @Query("SELECT b FROM Bookmark b JOIN b.user u WHERE u.username = ?1")
    List<Bookmark> findAllByUser(String username);

    @Query("SELECT b FROM Bookmark b JOIN b.folder f JOIN f.user u WHERE u.username = :username AND f.name = :folderName")
    List<Bookmark> findAllByUserAndFolder(String username, String folderName);

}
