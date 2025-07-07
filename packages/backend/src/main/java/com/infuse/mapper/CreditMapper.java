package com.infuse.mapper;

import com.infuse.dto.CreditDTO;
import com.infuse.model.Credit;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditMapper {

  CreditDTO toDTO(Credit credit);

  List<CreditDTO> toDTOList(List<Credit> credits);

  Credit toEntity(CreditDTO creditDTO);
}
