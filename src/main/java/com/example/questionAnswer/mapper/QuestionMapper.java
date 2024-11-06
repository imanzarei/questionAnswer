package com.example.questionAnswer.mapper;

import com.example.questionAnswer.dto.QuestionDTO;
import com.example.questionAnswer.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionDTO toDTO(Question question);

    Question toModel(QuestionDTO questionDTO);
}
