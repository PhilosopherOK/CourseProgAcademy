package com.example.hw17.services;

import com.example.hw17.models.Album;
import com.example.hw17.repositories.AlbumRepo;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Data
public class AlbumService {
    private final AlbumRepo albumRepo;

    @Transactional
    public List<Album> findAll(){
        return albumRepo.findAll();
    }
    @Transactional(readOnly = true)
    public Album findMain(){
        return albumRepo.findByNameStartingWith("MAIN").orElse(null);
    }

    @Transactional
    public void save(Album album){
        albumRepo.save(album);
    }

    @Transactional
    public Album findByName(String nameOfAlbum){
        return albumRepo.findByNameStartingWith(nameOfAlbum).orElse(findMain());
    }

}
