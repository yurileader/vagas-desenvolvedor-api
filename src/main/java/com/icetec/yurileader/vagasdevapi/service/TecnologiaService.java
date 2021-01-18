package com.icetec.yurileader.vagasdevapi.service;

import com.icetec.yurileader.vagasdevapi.model.Tecnologia;
import com.icetec.yurileader.vagasdevapi.model.dto.TecnologiaDTO;
import com.icetec.yurileader.vagasdevapi.repository.TecnologiaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TecnologiaService {

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TecnologiaDTO> listarTodos() {
        List<Tecnologia> tecnologias = tecnologiaRepository.findAll();
        return tecnologias.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TecnologiaDTO listarPorId(Long id) {
        Tecnologia tecnologia = encontrarTecnologia(id);

        return toDto(tecnologia);
    }

    public TecnologiaDTO tecnologiaSalvar(TecnologiaDTO tecnologiaDTO) {
        Tecnologia tecnologia = toEntity(tecnologiaDTO);
        Tecnologia tecnologiaSalva = tecnologiaRepository.save(tecnologia);

        return toDto(tecnologiaSalva);
    }

    public void tecnologiaDeletar (Long id) {
        Tecnologia tecnologia = encontrarTecnologia(id);
        tecnologiaRepository.deleteById(tecnologia.getId());
    }

    private Tecnologia encontrarTecnologia(Long id) {
        Tecnologia tecnologia = tecnologiaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return tecnologia;
    }

    private TecnologiaDTO toDto(Tecnologia tecnologia) {
        return modelMapper.map(tecnologia, TecnologiaDTO.class);
    }

    private Tecnologia toEntity(TecnologiaDTO tecnologiaDTO) {
        return modelMapper.map(tecnologiaDTO, Tecnologia.class);
    }
}
