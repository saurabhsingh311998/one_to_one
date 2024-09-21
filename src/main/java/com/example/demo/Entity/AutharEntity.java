package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AutharEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String fName;
    private String lName;
    private long mobileNumber;
    private int pin;

    // Correct the reference to BookEntity
    // Test Git
    @OneToOne
    @JoinColumn(name = "authar_book")
    private BookEntity book;
}
