package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TutorialRepository extends JpaRepository<TutorialEntity,Integer> {
    List<TutorialEntity> findByPublishedStatus(Boolean publishedStatus);
    List<TutorialEntity> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descriptionKeyword);

}
