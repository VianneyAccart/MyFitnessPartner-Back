package com.myfitnesspartner.service;

import com.myfitnesspartner.dto.CreateSessionDto;
import com.myfitnesspartner.entity.Exercise;
import com.myfitnesspartner.entity.Serie;
import com.myfitnesspartner.repository.SerieRepository;
import com.myfitnesspartner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SerieRepository serieRepository;

    public void createSession(@Valid CreateSessionDto createSessionDto) {
        CreateSessionDto session = new CreateSessionDto();
        session.setDate(createSessionDto.getDate());
        session.setFeeling(createSessionDto.getFeeling());
        if (createSessionDto.getName() != null) {
            session.setName(createSessionDto.getName());
        }
        if (createSessionDto.getNote() != null) {
            session.setNote(createSessionDto.getNote());
        }
        // TODO : change this method to get logged user
        session.setUser(userRepository.getById(1L));

        List<Exercise> exercises = new ArrayList<>();
        for (Exercise exercise : exercises) {
            Exercise currentExercise = new Exercise();
            currentExercise.setId(exercise.getId());

            List<Serie> series = new ArrayList<>();
            for (Serie serie : series) {
                Serie currentSerie = new Serie();
                currentSerie.setExercise(exercise); // Not sure
                currentSerie.setWeight(serie.getWeight());
                currentSerie.setRepetitions((serie.getRepetitions()));
                serieRepository.save(currentSerie);
            }
        }
    }
}
