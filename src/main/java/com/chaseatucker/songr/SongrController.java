package com.chaseatucker.songr;

import com.chaseatucker.songr.model.Album;
import com.chaseatucker.songr.model.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SongrController {

  @Autowired
  AlbumRepository albumsRepo;

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @RequestMapping(value = "/capitalize/{word}", method = RequestMethod.GET)
  public String capitalize(@PathVariable String word, Model m) {
    m.addAttribute("word", word);
    m.addAttribute("capitalizedWord", word.toUpperCase());
    return "capitalize";
  }

  @RequestMapping(value = "/albums", method = RequestMethod.GET)
  public String albums(Model m) {
    List albums = albumsRepo.findAll();
    m.addAttribute("albums", albums);
    return "albums";
  }

  @PostMapping("/albums")
  public RedirectView addAlbum(String title, String artist, int songCount, int length, String imageUrl) {
    Album newAlbum = new Album(title, artist, songCount, length, imageUrl);
    albumsRepo.save(newAlbum);
    return new RedirectView("/albums");
  }
}
