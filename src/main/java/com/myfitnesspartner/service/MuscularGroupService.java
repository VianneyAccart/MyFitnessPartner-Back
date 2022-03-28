package com.myfitnesspartner.service;

import com.myfitnesspartner.dto.CreateMuscularGroupDto;
import com.myfitnesspartner.entity.MuscularGroup;
import com.myfitnesspartner.repository.MuscularGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
public class MuscularGroupService {

    @Autowired
    MuscularGroupRepository muscularGroupRepository;

    public MuscularGroup getMuscularGroupById(@PathVariable() Long id) {
        return muscularGroupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<MuscularGroup> getMuscularGroups() {
        return muscularGroupRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public ResponseEntity<String> createMuscularGroup(CreateMuscularGroupDto createMuscularGroupDto) {
        MuscularGroup muscularGroup = new MuscularGroup();
        muscularGroup.setName(createMuscularGroupDto.getName());
        muscularGroupRepository.save(muscularGroup);
        return new ResponseEntity<>("Muscular group " + muscularGroup.getName() + " well created", HttpStatus.OK);
    }

    public ResponseEntity<String> modifyMuscularGroupById(@PathVariable() Long id, @Valid CreateMuscularGroupDto createMuscularGroupDto) {
        MuscularGroup muscularGroup = getMuscularGroupById(id);
        muscularGroup.setName(createMuscularGroupDto.getName());
        muscularGroupRepository.save(muscularGroup);
        return new ResponseEntity<>("Muscular group well modified", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteMuscularGroupById(@PathVariable() Long id) {
        MuscularGroup muscularGroup = getMuscularGroupById(id);
        muscularGroupRepository.deleteById(muscularGroup.getId());
        return new ResponseEntity<>("Muscular group well deleted", HttpStatus.OK);
    }
}
