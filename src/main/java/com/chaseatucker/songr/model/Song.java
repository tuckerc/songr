package com.chaseatucker.songr.model;

import javax.persistence.*;

@Entity
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false)
  private String title;
  @Column(nullable = false)
  private int length;
  @Column(nullable = false)
  private int trackNumber;

  @ManyToOne
  @JoinColumn(name = "album_id")
  private Album album;

  public Song() {}

  public Song(String title, int length, int trackNumber, Album album) {
    this.id = id;
    this.title = title;
    this.length = length;
    this.trackNumber = trackNumber;
    this.album = album;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getTrackNumber() {
    return trackNumber;
  }

  public void setTrackNumber(int trackNumber) {
    this.trackNumber = trackNumber;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }
}
