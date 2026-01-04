package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class BoardListPagingDTO {
    private List<BoardDTO> boardDTOList;
    private int totalCount;
    private int page;
    private int size;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private List<Integer> pageNums;

    public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size) {
	this.boardDTOList = boardDTOList;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;

	// start 계산을 위한 end 페이지
	int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

	this.start = tempEnd - 9;

	// start값이 1이 아니라면 이전 페이지로 이동 필요
	this.prev = this.start != 1;

	// 임시 end값 * size가 totalCount 보다 크다면 totalCount로 다시 계산 필요
	if ((tempEnd * size) > totalCount) {
	    this.end = (int) (Math.ceil(totalCount / (double) size));
	} else {
	    this.end = tempEnd;
	}

	// end값 * size 보다 totalCount 가 크다면 next 로 이동 가능
	this.next = totalCount > (this.end * size);

	// 화면에 출력한 번호들 계산
	this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
