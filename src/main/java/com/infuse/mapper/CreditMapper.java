package com.infuse.mapper;

import com.infuse.dto.CreditDTO;
import com.infuse.model.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditDTO toDTO(Credit credit);
    
    List<CreditDTO> toDTOList(List<Credit> credits);
    
    Credit toEntity(CreditDTO creditDTO);
} 