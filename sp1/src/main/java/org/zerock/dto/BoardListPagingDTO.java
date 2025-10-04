package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class BoardListPagingDTO {
	private List<BoardDTO> boardDTOList;
	private int totalCount;
	private int page, size;

	private int start, end;
	private boolean prev, next;
	private List<Integer> pageNums;

	public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size) {
		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;

		// start 계산을 위한 end 페이지
		int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

		this.start = tempEnd - 9;

		this.prev = this.start != 1;// start가 1이 아니면 이전 페이지로 이동 필요

		// 임시 end 값 * size 가 totalCount 보다 크다면 totalCount 다시 계산 필요
		if ((tempEnd * size) > totalCount) {
			this.end = (int) (Math.ceil(totalCount / (double) size));
		} else {
			this.end = tempEnd;
		}

		// end * size 보다 totalCount가 크다면 다음 페이지로 이동가능
		this.next = totalCount > (this.end * size);

		// 화면에 출력한 번호들 계산
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	}
}
