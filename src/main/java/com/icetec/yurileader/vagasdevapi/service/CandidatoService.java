package com.icetec.yurileader.vagasdevapi.service;

import com.icetec.yurileader.vagasdevapi.model.Candidato;
import com.icetec.yurileader.vagasdevapi.repository.CandidatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public Candidato candidatoAtualizar(Long id, Candidato candidato) {
        Candidato candidatoSalvo = candidatoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        BeanUtils.copyProperties(candidato, candidatoSalvo, "id");    //copia o candidato para o target(candidatoSalvo) ignorando o "id"

        return candidatoRepository.save(candidatoSalvo);
    }
}
