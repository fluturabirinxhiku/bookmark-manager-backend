package com.bookmarkmanagerbackend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
