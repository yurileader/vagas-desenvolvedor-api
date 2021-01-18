package com.icetec.yurileader.vagasdevapi.service;

import com.icetec.yurileader.vagasdevapi.model.Candidato;
import com.icetec.yurileader.vagasdevapi.model.dto.CandidatoDTO;
import com.icetec.yurileader.vagasdevapi.model.dto.CandidatoListagemDTO;
import com.icetec.yurileader.vagasdevapi.repository.CandidatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Candidato candidatoSalvar(Candidato candidato) {

        return candidatoRepository.save(candidato);
    }

    public CandidatoDTO candidatoAtualizar(Long id, CandidatoDTO candidatoDTO) {
        Candidato candidatoSalvo = encontrarCandidato(id);
        BeanUtils.copyProperties(candidatoDTO, candidatoSalvo, "id");    //copia o candidato para o target(candidatoSalvo) ignorando o "id"

        return toDto(candidatoRepository.save(candidatoSalvo));
    }


    public void candidatoDeletar(Long id) {
        Candidato candidato = encontrarCandidato(id);

        candidatoRepository.deleteById(candidato.getId());
    }

    public List<CandidatoDTO> listarTodos(){
        List<Candidato> candidato = candidatoRepository.findAll();
        return candidato.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CandidatoListagemDTO listarPorId(Long id){
        Candidato candidato = encontrarCandidato(id);

        return modelMapper.map(candidato, CandidatoListagemDTO.class);
    }

    private Candidato encontrarCandidato(Long id) {
        Candidato candidatoSalvo = candidatoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return candidatoSalvo;
    }

    private CandidatoDTO toDto(Candidato candidato) {
        return modelMapper.map(candidato, CandidatoDTO.class);
    }

    private Candidato toEntity(CandidatoDTO candidatoDTO){
        return modelMapper.map(candidatoDTO, Candidato.class);
    }

}
