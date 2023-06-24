package com.bookmarkmanagerbackend.repositories;

import com.bookmarkmanagerbackend.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
    @Query("SELECT f FROM Folder f JOIN f.user u WHERE u.username = ?1")
    List<Folder> findAllByUser(String username);

    boolean existsByName(String folderName);
}
