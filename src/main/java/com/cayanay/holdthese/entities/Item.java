package com.cayanay.holdthese.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "Items")
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @Column(nullable = false)
    private LocalDateTime unableAt;

    @Column(nullable = false)
    private Long duration;

}
