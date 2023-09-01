package com.example.demo.api;

import com.example.demo.dao.TutorialEntity;
import com.example.demo.dao.TutorialRepository;
import com.example.demo.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tutorial")
public class TutorialController {

    private TutorialService tutorialService;

@Autowired
    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }
    //Create Tutorial
    @PostMapping
    public TutorialEntity createTutorial(@RequestBody TutorialEntity tutorialEntity){

    return tutorialService.createTutorial(tutorialEntity);

    }
    //Get all tutorials from the database
    @GetMapping
    public List<TutorialEntity> getAllTutorials(){
    return tutorialService.getAllTutorials();

    }
    //Get tutorial based on ID
    @GetMapping("{id}")
    public TutorialEntity getTutorialById(@PathVariable(value ="id") Integer tutorialId){
    return tutorialService.getTutorialById(tutorialId);
    }

    //Update tutorial by ID
    @PutMapping("{id}")
    public ResponseEntity<TutorialEntity> updateTutorialById(@PathVariable(value = "id") Integer tutorialId, @RequestBody Map<String,Object> tutorialEntity){
    return tutorialService.updateTutorialById(tutorialId,tutorialEntity);
    }

    //Delete tutorial by ID
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTutorialById(@PathVariable(value = "id")Integer tutorialId){

    return tutorialService.deleteTutorialById(tutorialId);

    }

    //Delete all tutorials
    @DeleteMapping
    public String deleteTutorial(){
    return tutorialService.deleteTutorial();
    }
    //Does it matter that all these implementations are here and not in the service class: Apparently it does

    //Get published tutorials
    @GetMapping("/published")
    public List<TutorialEntity> getTutorialByPublished(){
        return tutorialService.getTutorialByPublished();
    }

    //Getting a tutorial containing a search keyword the user input
    @GetMapping("/search")
    public List<TutorialEntity> getTutorialsByKeyword(@RequestParam(required = false) String titleKeyword,
                                                      @RequestParam(required = false) String descriptionKeyword,
                                                      @RequestParam(required = false) Boolean publishedStatusKeyword){

        List<TutorialEntity> tutorialEntities = tutorialService.getTutorialsByKeyword(titleKeyword,descriptionKeyword,publishedStatusKeyword);
            return tutorialEntities;

        }




}
