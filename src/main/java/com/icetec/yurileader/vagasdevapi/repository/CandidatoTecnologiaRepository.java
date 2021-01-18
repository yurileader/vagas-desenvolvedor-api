package com.icetec.yurileader.vagasdevapi.repository;

import com.icetec.yurileader.vagasdevapi.model.CandidatoTecnologia;
import com.icetec.yurileader.vagasdevapi.model.CandidatoTecnologiaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoTecnologiaRepository extends JpaRepository<CandidatoTecnologia, CandidatoTecnologiaId> {
}
