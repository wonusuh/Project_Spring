package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BoardListPagingDTO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class BoardService {
    private final BoardMapper boardMapper;

    public List<BoardDTO> getList() {
	return boardMapper.list();
    }

    public Long register(BoardDTO dto) {
	int insertCount = boardMapper.insert(dto);
	log.info("insertCount : " + insertCount);
	return dto.getBno();
    }

    public BoardDTO read(Long bno) {
	BoardDTO boardDTO = boardMapper.selectOne(bno);
	return boardDTO;
    }

    public void remove(Long bno) {
	boardMapper.remove(bno);
    }

    public void modify(BoardDTO boardDTO) {
	boardMapper.update(boardDTO);
    }

    public BoardListPagingDTO getList(int page, int size, String typeStr, String keyword) {
	// 페이지 번호가 0보다 작으면 무조건 1 페이지
	page = page <= 0 ? 1 : page;

	// 사이즈가 10보다 작거나 100보다 크면 10
	size = (size <= 10 || size > 100) ? 10 : size;

	// 2페이지라면 (2-1) * 10 이 되어야 함
	int skip = (page - 1) * size;

	String[] types = typeStr == null ? null : typeStr.split("");
	List<BoardDTO> list = boardMapper.listSearch(skip, size, types, keyword);// 게시글 목록
	int total = boardMapper.listCountSearch(types, keyword);// 조건으로 검색된 게시글 개수
	return new BoardListPagingDTO(list, total, page, size, typeStr, keyword);
    }
}
