package com.example.hw17.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String PathOfPhoto;
    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    public Photo(String pathOfPhoto, Album album) {
        PathOfPhoto = pathOfPhoto;
        this.album = album;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", PathOfPhoto='" + PathOfPhoto + '\'' +
                ", album=" + album +
                '}';
    }
}
