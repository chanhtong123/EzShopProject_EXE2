package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

}