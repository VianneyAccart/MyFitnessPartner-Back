package com.myfitnesspartner.service;

import com.myfitnesspartner.dto.CreateExerciseDto;
import com.myfitnesspartner.entity.Exercise;
import com.myfitnesspartner.entity.MuscularGroup;
import com.myfitnesspartner.repository.ExerciseRepository;
import com.myfitnesspartner.repository.MuscularGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    MuscularGroupService muscularGroupService;

    @Autowired
    MuscularGroupRepository muscularGroupRepository;

    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(@PathVariable() Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<String> createExercise(@Valid CreateExerciseDto createExerciseDto) {
        Exercise exercise = new Exercise();
        exercise.setName(createExerciseDto.getName());
        exercise.setDescription(createExerciseDto.getDescription());
        MuscularGroup muscularGroup = muscularGroupService.getMuscularGroupById(createExerciseDto.getMuscularGroupId());
        exercise.setMuscularGroup(muscularGroup);
        exercise.setLastUpdate(LocalDateTime.now());
        exerciseRepository.save(exercise);
        return new ResponseEntity<>("Exercise " + exercise.getName() + " well created", HttpStatus.OK);
    }

    public ResponseEntity<String> modifyExerciseById(@PathVariable() Long id, @Valid CreateExerciseDto createExerciseDto) {
        Exercise exercise = getExerciseById(id);
        exercise.setName(createExerciseDto.getName());
        exercise.setLastUpdate(LocalDateTime.now());
        exercise.setDescription(createExerciseDto.getDescription());
        MuscularGroup muscularGroup = muscularGroupService.getMuscularGroupById(createExerciseDto.getMuscularGroupId());
        exercise.setMuscularGroup(muscularGroup);
        exerciseRepository.save(exercise);
        return new ResponseEntity<>("Exercise well modified", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteExerciseById(@PathVariable() Long id) {
        Exercise exercise = getExerciseById(id);
        exerciseRepository.deleteById(exercise.getId());
        return new ResponseEntity<>("Exercise well deleted", HttpStatus.OK);
    }

    public List<Exercise> getExercisesByMuscularGroupId(@PathVariable() Long id) {
        MuscularGroup muscularGroup = muscularGroupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Exercise> exercises = new ArrayList<>(muscularGroup.getExercises());
        exercises.sort(Exercise.sortByAscendingName);
        return exercises;
    }
}
