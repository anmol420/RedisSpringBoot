package com.anmol420.redis_springboot.mappers;

import com.anmol420.redis_springboot.domain.dtos.BookDTO;
import com.anmol420.redis_springboot.domain.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookDTO toDTO(Book book);

    Book fromDTO(BookDTO bookDTO);

}
