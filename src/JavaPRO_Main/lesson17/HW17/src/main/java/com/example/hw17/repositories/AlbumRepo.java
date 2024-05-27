package com.example.hw17.repositories;

import com.example.hw17.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumRepo extends JpaRepository<Album, Long> {
    Optional<Album> findByNameStartingWith(String name);
}
