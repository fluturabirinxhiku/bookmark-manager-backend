package com.bookmarkmanagerbackend.config;

import com.bookmarkmanagerbackend.models.Folder;
import com.bookmarkmanagerbackend.services.FolderService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class FolderDeserializer extends JsonDeserializer<Folder> {
    private final FolderService folderService;

    public FolderDeserializer(FolderService folderService) {
        this.folderService = folderService;
    }

    @Override
    public Folder deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.readValueAs(String.class);
        if (value != null && value.isEmpty()) {
            return null;
        }
        Integer folderId = Integer.parseInt(value);
        return folderService.findById(folderId);
    }
}
