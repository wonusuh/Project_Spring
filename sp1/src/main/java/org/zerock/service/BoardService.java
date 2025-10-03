package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.BoardDTO;
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
		log.info("getList..............................................................");
		return boardMapper.list();
	}

	public Long register(BoardDTO dto) {
		int insertCount = boardMapper.insert(dto);
		log.info("register.............................................................." + insertCount);
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
}
