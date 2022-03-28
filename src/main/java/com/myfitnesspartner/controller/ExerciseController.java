package com.myfitnesspartner.controller;

import com.myfitnesspartner.dto.CreateExerciseDto;
import com.myfitnesspartner.entity.Exercise;
import com.myfitnesspartner.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/exercises")
    public List<Exercise> getExercises() {
        return exerciseService.getExercises();
    }

    @GetMapping("/exercise/{id}")
    public Exercise getExerciseById(@PathVariable() Long id) {
        return exerciseService.getExerciseById(id);
    }

    @GetMapping("/muscular-group/{id}/exercises")
    public List<Exercise> getExercisesByMuscularGroupId(@PathVariable() Long id) {
        return exerciseService.getExercisesByMuscularGroupId(id);
    }

    @PostMapping("/exercise/create")
    public ResponseEntity<String> createExercise(@Valid CreateExerciseDto createExerciseDto) {
        return exerciseService.createExercise(createExerciseDto);
    }

    @PatchMapping("/exercise/{id}/modify")
    public ResponseEntity<String> modifyExerciseById(@PathVariable() Long id, @Valid CreateExerciseDto createExerciseDto) {
        return exerciseService.modifyExerciseById(id, createExerciseDto);
    }

    @DeleteMapping("/exercise/{id}/delete")
    public ResponseEntity<String> deleteExerciseById(@PathVariable() Long id) {
        return exerciseService.deleteExerciseById(id);
    }
}
