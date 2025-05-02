package br.com.jmtech.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    private Integer totalRecords;
    private Integer totalPages;
    private Integer pageNumber;
    private Integer pageSize;
}
