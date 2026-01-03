package org.zerock.dto;

import java.util.List;

import lombok.Data;

@Data
public class BoardListPagingDTO {
    private List<BoardDTO> boardDTOList;
    private int totalCount;
    private int page;
    private int size;

    public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size) {
	this.boardDTOList = boardDTOList;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;
    }
}
