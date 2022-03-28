package com.myfitnesspartner.controller;

import com.myfitnesspartner.dto.CreateMuscularGroupDto;
import com.myfitnesspartner.entity.MuscularGroup;
import com.myfitnesspartner.service.MuscularGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class MuscularGroupController {

    @Autowired
    MuscularGroupService muscularGroupService;

    @GetMapping("/muscular-groups")
    public List<MuscularGroup> getMuscularGroups() {
        return muscularGroupService.getMuscularGroups();
    }

    @GetMapping("/muscular-group/{id}")
    public MuscularGroup getMuscularGroupById(@PathVariable() Long id) {
        return muscularGroupService.getMuscularGroupById(id);
    }

    @PostMapping("/muscular-group/create")
    public ResponseEntity<String> createMuscularGroup(@Valid CreateMuscularGroupDto createMuscularGroupDto) {
        return muscularGroupService.createMuscularGroup(createMuscularGroupDto);
    }

    @PatchMapping("/muscular-group/{id}/modify")
    public ResponseEntity<String> modifyMuscularGroupById(@PathVariable() Long id, @Valid CreateMuscularGroupDto createMuscularGroupDto) {
        return muscularGroupService.modifyMuscularGroupById(id, createMuscularGroupDto);
    }

    @DeleteMapping("/muscular-group/{id}/delete")
    public ResponseEntity<String> deleteMuscularGroupById(@PathVariable() Long id) {
        return muscularGroupService.deleteMuscularGroupById(id);
    }
}
