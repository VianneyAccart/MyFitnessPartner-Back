package com.myfitnesspartner.repository;

import com.myfitnesspartner.entity.MuscularGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscularGroupRepository extends JpaRepository<MuscularGroup, Long> {
}
