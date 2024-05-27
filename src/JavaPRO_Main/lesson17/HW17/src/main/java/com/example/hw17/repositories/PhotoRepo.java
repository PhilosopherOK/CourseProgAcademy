package com.example.hw17.repositories;

import com.example.hw17.models.Album;
import com.example.hw17.models.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
    public Page<Photo> findAllByAlbum(Album album, Pageable pageable);
}
