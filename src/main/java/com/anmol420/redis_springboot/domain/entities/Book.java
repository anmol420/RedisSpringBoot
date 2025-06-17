package com.anmol420.redis_springboot.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(description, book.description) && Objects.equals(authorName, book.authorName) && Objects.equals(createdAt, book.createdAt) && Objects.equals(updatedAt, book.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, authorName, createdAt, updatedAt);
    }
}
