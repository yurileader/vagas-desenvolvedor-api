package com.icetec.yurileader.vagasdevapi.repository;

import com.icetec.yurileader.vagasdevapi.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Candidato findByEmail(String email);

}
