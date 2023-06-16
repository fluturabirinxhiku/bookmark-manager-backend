package com.bookmarkmanagerbackend.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String url;
    private String description;
    @CreationTimestamp
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder;

}
