package com.example.demo.dto.common;

public class ResultPaginationDTO {
    private PaginationDTO paginationDTO;
    private Object data;

    public PaginationDTO getPaginationDTO() {
        return paginationDTO;
    }

    public void setPaginationDTO(PaginationDTO paginationDTO) {
        this.paginationDTO = paginationDTO;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
