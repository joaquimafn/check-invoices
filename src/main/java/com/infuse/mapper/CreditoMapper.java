package com.infuse.mapper;

import com.infuse.dto.CreditoDTO;
import com.infuse.model.Credito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditoMapper {

    CreditoDTO toDTO(Credito credito);
    
    List<CreditoDTO> toDTOList(List<Credito> creditos);
    
    Credito toEntity(CreditoDTO creditoDTO);
} 