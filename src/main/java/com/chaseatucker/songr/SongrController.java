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

  @PostMapping("/albums/addAlbum")
  public RedirectView addAlbum(String title, String artist, int songCount, int length, String imageUrl) {
    Album newAlbum = new Album(title, artist, songCount, length, imageUrl);
    albumsRepo.save(newAlbum);
    return new RedirectView("/albums");
  }

  @GetMapping("/albums/{id}")
  public String album(@PathVariable String id, Model m) {
    Album album = albumsRepo.getOne(Long.parseLong(id));
    m.addAttribute("album", album);
    return "album";
  }

  @PostMapping("/albums/{id}/addSong")
  public RedirectView albumsAddSong(@PathVariable String id, String title, int length, int trackNumber) {
    Album songAlbum = albumsRepo.getOne(Long.parseLong(id));
    Song newSong = new Song(title, length, trackNumber, songAlbum);
    songRepo.save(newSong);
    return new RedirectView("/albums/");
  }

  @PostMapping("/albums/{id}/songs/addSong")
  public RedirectView albumAddSong(@PathVariable String id, String title, int length, int trackNumber) {
    Album songAlbum = albumsRepo.getOne(Long.parseLong(id));
    Song newSong = new Song(title, length, trackNumber, songAlbum);
    songRepo.save(newSong);
    return new RedirectView("/albums/" + id);
  }

  @PostMapping("/albums/{id}/delete")
  public RedirectView deleteAlbum(@PathVariable String id) {
    albumsRepo.deleteById(Long.parseLong(id));
    return new RedirectView("/albums");
  }

  @PostMapping("/albums/{albumID}/songs/{songID}/delete")
  public RedirectView albumDeleteSong(@PathVariable String albumID, @PathVariable String songID) {
    songRepo.deleteById(Long.parseLong(songID));
    return new RedirectView("/albums/" + albumID);
  }

  @PostMapping("/albums/{albumID}/deleteSong/{songID}")
  public RedirectView albumsDeleteSong(@PathVariable String albumID, @PathVariable String songID) {
    songRepo.deleteById(Long.parseLong(songID));
    return new RedirectView("/albums");
  }
}
