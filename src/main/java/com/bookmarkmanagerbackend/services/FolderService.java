package com.bookmarkmanagerbackend.services;

import com.bookmarkmanagerbackend.exceptions.FolderNotFoundException;
import com.bookmarkmanagerbackend.models.Folder;
import com.bookmarkmanagerbackend.repositories.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {
    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public Folder findById(Integer id) {
        return folderRepository.findById(id).orElseThrow(() -> new FolderNotFoundException(id));
    }

    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    public void deleteById(Integer id) {
        folderRepository.deleteById(id);
    }

    public List<Folder> findAllByUserUsername(String username){
        return folderRepository.findAllByUserUsername(username);
    }
}
