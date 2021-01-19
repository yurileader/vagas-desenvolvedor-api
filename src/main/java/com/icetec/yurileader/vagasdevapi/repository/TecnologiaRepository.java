package com.icetec.yurileader.vagasdevapi.repository;

import com.icetec.yurileader.vagasdevapi.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "delete from CandidatoTecnologia ct where ct.idTecnologia =?1")
    Integer deleteTecnologiaBy(Long id);
}
