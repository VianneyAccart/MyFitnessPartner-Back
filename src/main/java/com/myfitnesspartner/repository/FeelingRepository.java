package com.myfitnesspartner.repository;

import com.myfitnesspartner.entity.Feeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeelingRepository extends JpaRepository<Feeling, Long> {
}
