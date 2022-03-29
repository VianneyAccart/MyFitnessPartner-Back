package com.myfitnesspartner.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myfitnesspartner.dto.CreateSessionDto;
import com.myfitnesspartner.dto.ExerciseDto;
import com.myfitnesspartner.dto.SerieDto;
import com.myfitnesspartner.entity.*;
import com.myfitnesspartner.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.DataInput;
import java.io.IOException;
import java.time.LocalDate;
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

    public void createSession(CreateSessionDto createSessionDto) throws IOException {
        // CREATE SESSION
        Session session = new Session();
        // TODO : rendre dynamique la date
        session.setDate(LocalDate.of(2022, 3, 29));
        System.err.println("Avant feeling");
        Feeling feeling = feelingRepository.findById(5L)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        System.err.println("Feeling trouvé");
        session.setFeeling(feeling);
        System.err.println("Après feeling");
        if (createSessionDto.getName() != null) {
            session.setName(createSessionDto.getName());
        }
        if (createSessionDto.getNote() != null) {
            session.setNote(createSessionDto.getNote());
        }
        System.err.println("Session sans l'user");
        // TODO : change this method to get logged user
        User user = userRepository.findById(1L)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        session.setUser(user);
        System.err.println("Session User ID OK");
        sessionRepository.save(session);
        System.err.println("Session enregistrée");

        // GET EXISTING SESSION
        Session existingSession = sessionRepository.findById(session.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<SessionExercise> sessionExercises = new ArrayList<>();
        System.err.println("Liste de sessionExercises initialisée");

        // CREATE SESSION_EXERCISES
        // TODO : createSessionDto.getExercises() is a String[] and not List<ExerciseDto>

        System.err.println("Avant mapper" + createSessionDto.getExercises());

        ObjectMapper mapper = new ObjectMapper();
        List<ExerciseDto> list = mapper.readValue((JsonParser) createSessionDto.getExercises(), new TypeReference<>() {
        });

        System.err.println("Après mapper" + list);



        for (ExerciseDto exerciseDto : list) {
            SessionExercise sessionExercise = new SessionExercise();
            System.err.println("Nouvelle session créée");
            Exercise exercise = exerciseRepository.findById(exerciseDto.getExerciseId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            sessionExercise.setExercise(exercise);
            sessionExercise.setSession(existingSession);
            sessionExerciseRepository.save(sessionExercise);
            System.err.println("Nouvelle session sauvegardée dans la DB");
            sessionExercises.add(sessionExercise);
            System.err.println("Nouvelle session ajoutée à la liste de sessionExercise");

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
    }
}
