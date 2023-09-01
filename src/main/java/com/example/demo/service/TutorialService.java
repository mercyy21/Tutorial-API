package com.example.demo.service;

import com.example.demo.dao.TutorialEntity;
import com.example.demo.dao.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TutorialService {

    TutorialRepository tutorialRepository;

@Autowired
    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }
//Create Tutorial
    public TutorialEntity createTutorial(TutorialEntity tutorialEntity){
    TutorialEntity savedTutorial = tutorialRepository.save(tutorialEntity);
    return savedTutorial;
    }
    //Get all tutorials from the database
    public List<TutorialEntity> getAllTutorials(){
        List<TutorialEntity> allTutorials = tutorialRepository.findAll();
        return allTutorials;
    }
    //Get tutorial by ID
    public TutorialEntity getTutorialById(Integer tutorialId){
        TutorialEntity tutorialEntity = tutorialRepository.findById(tutorialId).get();
        return tutorialEntity;
    }
    //Update tutorial by ID
    public ResponseEntity<TutorialEntity> updateTutorialById(Integer tutorialId,Map<String,Object> tutorialEntity){
        Optional<TutorialEntity> optionalTutorial = tutorialRepository.findById(tutorialId);//Used optional for null safety purposes

        if(!optionalTutorial.isPresent()){
            return ResponseEntity.notFound().build();
        }
        TutorialEntity existingTutorial = optionalTutorial.get();
        for (Map.Entry<String, Object> entry : tutorialEntity.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Update fields based on the keys provided in the request
            switch (key) {
                case "title":
                    existingTutorial.setTitle((String) value);
                    break;
                case "description":
                    existingTutorial.setDescription((String) value);
                    break;
                case "published":
                    existingTutorial.setPublishedStatus((boolean) value);
                    break;
                // Add cases for other fields you want to support
            }
        }
        // Passed the new values into the already existing tutorial thereby updating it
        final TutorialEntity updatedTutorial = tutorialRepository.save(existingTutorial);
        return ResponseEntity.ok(updatedTutorial);
    }
    //Delete tutorial by ID
    public ResponseEntity<HttpStatus> deleteTutorialById(Integer tutorialId){
        TutorialEntity tutorialToDelete = tutorialRepository.findById(tutorialId).orElse(null);
        if(tutorialToDelete==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tutorialRepository.delete(tutorialToDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //Delete all tutorials
    public String deleteTutorial(){

        tutorialRepository.deleteAll();
        return "All records Deleted Successfully";
    }

    //Get published tutorials
    public List<TutorialEntity> getTutorialByPublished(){
        List<TutorialEntity> tutorialEntity = tutorialRepository.findByPublishedStatus(true);
        return tutorialEntity;
    }

    //Getting a tutorial containing a search keyword the user input
    public List<TutorialEntity> getTutorialsByKeyword( String titleKeyword,
                                                       String descriptionKeyword,
                                                       Boolean publishedStatusKeyword){

        List<TutorialEntity> tutorialEntities = tutorialRepository.findByTitleContainingOrDescriptionContaining(titleKeyword,descriptionKeyword);

        if (publishedStatusKeyword!=null) {
            tutorialEntities = tutorialRepository.findByPublishedStatus(publishedStatusKeyword);
        }
        return tutorialEntities;


    }




}
