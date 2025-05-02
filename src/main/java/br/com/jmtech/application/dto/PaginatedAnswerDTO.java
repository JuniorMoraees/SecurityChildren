package br.com.jmtech.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PaginatedAnswerDTO <T>{

    List<T> answerContent;
    PageDTO pageMetaData;
}
