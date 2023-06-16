package com.bookmarkmanagerbackend.repositories;

import com.bookmarkmanagerbackend.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
    List<Folder> findAllByUserUsername(String username);
}
