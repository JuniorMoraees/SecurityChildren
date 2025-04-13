package br.com.jmtech.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class DetailDTO {

    private String detail;
    private String title;
    private Integer status;
}
