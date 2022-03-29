package com.myfitnesspartner.service;

import com.myfitnesspartner.dto.CreateSessionDto;
import com.myfitnesspartner.dto.ExerciseDto;
import com.myfitnesspartner.dto.SerieDto;
import com.myfitnesspartner.entity.*;
import com.myfitnesspartner.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SerieRepository serieRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionExerciseRepository sessionExerciseRepository;

    @Autowired
    FeelingRepository feelingRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    public ResponseEntity<String> createSession(@Valid @RequestBody CreateSessionDto createSessionDto) {

        Session session = new Session();
        session.setDate(createSessionDto.getDate());

        Feeling feeling = feelingRepository.findById(createSessionDto.getFeelingId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        session.setFeeling(feeling);

        if (createSessionDto.getName() != null) {
            session.setName(createSessionDto.getName());
        }
        if (createSessionDto.getNote() != null) {
            session.setNote(createSessionDto.getNote());
        }

        // TODO : change this method to get logged user
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        session.setUser(user);
        sessionRepository.save(session);

        Session existingSession = sessionRepository.findById(session.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<SessionExercise> sessionExercises = new ArrayList<>();

        for (ExerciseDto exerciseDto : createSessionDto.getExercises()) {
            SessionExercise sessionExercise = new SessionExercise();
            Exercise exercise = exerciseRepository.findById(exerciseDto.getExerciseId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            sessionExercise.setExercise(exercise);
            sessionExercise.setSession(existingSession);
            sessionExerciseRepository.save(sessionExercise);
            sessionExercises.add(sessionExercise);

            SessionExercise existingSessionExercise = sessionExerciseRepository.findById(sessionExercise.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            List<Serie> series = new ArrayList<>();
            for (SerieDto serie : exerciseDto.getSeries()) {
                Serie currentSerie = new Serie();
                currentSerie.setWeight(serie.getWeight());
                currentSerie.setRepetitions((serie.getRepetitions()));
                currentSerie.setSessionExercise(existingSessionExercise);
                series.add(currentSerie);
                serieRepository.save(currentSerie);
            }
            existingSessionExercise.setSeries(series);
            sessionExerciseRepository.save(existingSessionExercise);
        }
        session.setSessionExercises(sessionExercises);
        sessionRepository.save(session);
        return new ResponseEntity<>("Session well created", HttpStatus.OK);
    }
}
