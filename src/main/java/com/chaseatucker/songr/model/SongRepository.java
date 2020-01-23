package com.chaseatucker.songr.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
  public List<Song> findByTitleAndAlbum(String title, Album album);
}
