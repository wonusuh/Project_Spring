package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ReplyListPagingDTO {
    private List<ReplyDTO> replyDTOList;
    private int totalCount;
    private int page;
    private int size;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;
    private List<Integer> pageNums;

    public ReplyListPagingDTO(List<ReplyDTO> replyDTOList, int totalCount, int page, int size) {
	this.replyDTOList = replyDTOList;
	this.totalCount = totalCount;
	this.page = page;
	this.size = size;

	// start 계산을 위한 end 페이지
	int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
	this.start = tempEnd - 9;

	// start 값이 1이 아니라면 prev 활성화
	this.prev = start != 1;

	// 임시 end값 * size가 totalCount 보다 크다면 totalCount 로 다시계산 필요
	if ((tempEnd * size) > totalCount) {
	    this.end = (int) (Math.ceil(totalCount / (double) size));
	} else {
	    this.end = tempEnd;
	}

	// end * size 보다 totalCount 가 크다면 next 활성화
	this.next = totalCount > (this.end * size);

	// 화면에 출력한 번호들 계산
	this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }
}
