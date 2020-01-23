package com.chaseatucker.songr;

import com.chaseatucker.songr.model.Album;
import com.chaseatucker.songr.model.AlbumRepository;
import com.chaseatucker.songr.model.Song;
import com.chaseatucker.songr.model.SongRepository;
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
  @Autowired
  SongRepository songRepo;

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/capitalize/{word}")
  public String capitalize(@PathVariable String word, Model m) {
    m.addAttribute("word", word);
    m.addAttribute("capitalizedWord", word.toUpperCase());
    return "capitalize";
  }

  @GetMapping("/albums")
  public String albums(Model m) {
    List<Album> albums = albumsRepo.findAll();
    m.addAttribute("albums", albums);
    return "albums";
  }

  @PostMapping("/albums")
  public RedirectView addAlbum(String title, String artist, int songCount, int length, String imageUrl) {
    Album newAlbum = new Album(title, artist, songCount, length, imageUrl);
    albumsRepo.save(newAlbum);
    return new RedirectView("/albums");
  }

  @PostMapping("/albums/addSong")
  public RedirectView albumsAddSong(String title, int length, int trackNumber, String album) {
    List<Album> songAlbum = albumsRepo.findByTitle(album);
    Song newSong = new Song(title, length, trackNumber, songAlbum.get(0));
    songRepo.save(newSong);
    return new RedirectView("/albums");
  }

  @GetMapping("/album/{id}")
  public String album(@PathVariable String id, Model m) {
    Album album = albumsRepo.getOne(Long.parseLong(id));
    m.addAttribute("album", album);
    return "album";
  }

  @PostMapping("/album/addSong")
  public RedirectView albumAddSong(String title, int length, int trackNumber, String album) {
    List<Album> songAlbum = albumsRepo.findByTitle(album);
    Song newSong = new Song(title, length, trackNumber, songAlbum.get(0));
    songRepo.save(newSong);
    List<Song> song = songRepo.findByTitleAndAlbum(newSong.getTitle(), newSong.getAlbum());
    return new RedirectView("/album/" + song.get(0).getAlbum().getId());
  }
}
