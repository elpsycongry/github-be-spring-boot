package com.fileservice.ws.repository;

import com.fileservice.ws.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IVideoRepository extends JpaRepository<Video, Long> {
    @Query("select v.uri from Video v")
    List<String> findAllUri();
}
