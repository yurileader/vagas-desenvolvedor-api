package com.icetec.yurileader.vagasdevapi.repository;

import com.icetec.yurileader.vagasdevapi.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Candidato findByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from CandidatoTecnologia ct where ct.idCandidato =?1")
    Integer deleteCandidatoById(Long id);

}
