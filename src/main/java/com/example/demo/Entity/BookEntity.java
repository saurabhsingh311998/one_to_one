package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int bookId;
    private String bookName;
    private int part;

    // Correct the mappedBy field to reference the correct variable name in AutharEntity
    @OneToOne(mappedBy = "book")
    private AutharEntity authar;
}
