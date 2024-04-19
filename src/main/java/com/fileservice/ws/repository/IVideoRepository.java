package com.fileservice.ws.repository;

import com.fileservice.ws.model.User;
import com.fileservice.ws.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface IVideoRepository extends JpaRepository<Video, Long> {
    @Query("select v.uri from Video v")
    List<String> findAllUri();
    @Query("select v.timeCreate from Video v where v.user = :userID")
    List<LocalDate> findAddVideoTimeByUserID(@Param("userID") User user);

}
