package com.example.hw17.services;

import com.example.hw17.models.Album;
import com.example.hw17.models.Photo;
import com.example.hw17.repositories.AlbumRepo;
import com.example.hw17.repositories.PhotoRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class PhotoService {
    private final PhotoRepo photoRepo;
private final AlbumRepo albumRepo;
    @Value("${upload.path}")
    private String uploadPath;

    public String uploadImageAndGetPathOfPhoto(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload an empty file");
        }
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            throw new IllegalArgumentException("File should be png/jpeg ");
        }
        long maxFileSize = 10 * 1024 * 1024; // 10 MB in bytes
        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("File size exceeds 10 MB limit");
        }
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadPath, fileName);
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Error occurred while saving the file: " + e.getMessage(), e);
        }

        return fileName;
    }


    @Transactional
    public Photo save(Photo photo) {
        return photoRepo.save(photo);
    }

    @Transactional(readOnly = true)
    public Page<Photo> findAll(String nameOfAlbum, int page, int size) {
        Album album = albumRepo.findByNameStartingWith(nameOfAlbum).orElse(albumRepo.findByNameStartingWith("MAIN").get());

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        return photoRepo.findAllByAlbum(album, pageable);
    }

    @Transactional
    public void changeAlbum(Album album, List <Long> photoIds){
        List<Photo> allByIds = photoRepo.findAllById(photoIds);
        for(Photo photo: allByIds){
            photo.setAlbum(album);
        }
        albumRepo.save(album);
        photoRepo.saveAll(allByIds);
    }
}
