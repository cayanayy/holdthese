package com.cayanay.holdthese.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Items")
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(nullable = false, name = "access_code")
    private AccessCode accessCode;

    @OneToMany
    @JoinColumn(name = "item_id")
    private List<File> files;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime unableAt;

    @Column(nullable = false)
    private Long duration;

    public Item(String message, AccessCode accessCode, Long duration, List<File> files) {
        this.message = message;
        this.accessCode = accessCode;
        this.duration = duration;
        this.files = files;
    }
}
