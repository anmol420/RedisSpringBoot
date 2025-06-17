package com.anmol420.redis_springboot.repository;

import com.anmol420.redis_springboot.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    boolean existsByNameIgnoreCase(String name);
    Optional<Book> findByName(String name);

}
