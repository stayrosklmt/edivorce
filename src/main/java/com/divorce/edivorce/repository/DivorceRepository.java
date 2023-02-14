package com.divorce.edivorce.repository;

import com.divorce.edivorce.model.Divorce;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DivorceRepository extends JpaRepository<Divorce, UUID> {
    Optional<Divorce> findById(UUID id);
    List<Divorce> findByLawyerOne(UUID id);
    List<Divorce> findByLawyerTwo(UUID id);

    List<Divorce> findAll();

    List<Divorce> findAllByHusbandOne(UUID id);
    List<Divorce> findAllByHusbandTwo(UUID id);

    Divorce deleteDivorceById(UUID id);
}
