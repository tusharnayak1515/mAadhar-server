package com.mAadhar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mAadhar.entities.Aadhar;

@Repository
public interface AadharRepo extends JpaRepository<Aadhar, Long> {

    Aadhar findByUser_CitizenId(int citizenId);

    List<Aadhar> findByStatus(String status);
}
