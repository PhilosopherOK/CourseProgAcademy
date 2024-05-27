package com.example.hw17.controllers;

import com.example.hw17.models.Album;
import com.example.hw17.models.Photo;
import com.example.hw17.services.AlbumService;
import com.example.hw17.services.PhotoService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/main")
@Data
public class MainController {
    private final PhotoService photoService;
    private final AlbumService albumService;

    @GetMapping("/{nameOfAlbum}/{page}")
    public String getMainPage(@PathVariable(value = "page", required = false) Integer page,
                              @PathVariable(value = "nameOfAlbum", required = false) String nameOfAlbum,
                              RedirectAttributes redirectAttributes, Model model) {
        try {
            if (page == null) {
                page = 0;
            }
            if (nameOfAlbum == null) {
                nameOfAlbum = "MAIN";
            }
            model.addAttribute("photos", photoService.findAll(nameOfAlbum, page, 5));
            model.addAttribute("albumsName", albumService.findAll().stream().map(a -> a.getName()).toList());
            model.addAttribute("selectedNameOfAlbum", nameOfAlbum);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("redirectUrl", "/main/MAIN/0");
        }
        return "index";
    }

    @GetMapping("/getAnyPage")
    public String getAnyMain(@RequestParam("nameOfAlbum") String nameOfAlbum,
                             RedirectAttributes redirectAttributes){
        try {
            return "redirect:/main/"+nameOfAlbum+"/0";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/main/MAIN/0";
        }
    }

    @PostMapping("/createAlbum")
    public String createAlbum(@RequestParam("nameOfAlbum") String nameOfAlbum,
                              RedirectAttributes redirectAttributes) {
        try {
            albumService.save(new Album(nameOfAlbum));
            return "redirect:/main/MAIN/0";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/main/MAIN/0";
        }
    }


    @PostMapping("/postPhoto")
    public String postPhoto(@RequestParam("multipartFile") MultipartFile multipartFile,
                            RedirectAttributes redirectAttributes) {
        try {
            Album album = albumService.findMain();
            String pathOfPhoto = photoService.uploadImageAndGetPathOfPhoto(multipartFile);
            photoService.save(new Photo(pathOfPhoto, album));
            System.out.println("___________________________________________________________________________" + pathOfPhoto);
            return "redirect:/main/MAIN/0";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/main/MAIN/0";
        }
    }

    @PostMapping("/changeAlbum")
    public String changeAlbum(@RequestParam("nameOfAlbum") String nameOfAlbum,
                              @RequestParam("photoIds") List<Long> photoIds,
                              RedirectAttributes redirectAttributes) {
        try {
            photoService.changeAlbum(albumService.findByName(nameOfAlbum), photoIds);
            return "redirect:/main/"+nameOfAlbum+"/0";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/main/MAIN/0";
        }
    }
}
