package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.dto.ReplyDTO;

public interface ReplyMapper {
    int insert(ReplyDTO replyDTO);

    ReplyDTO read(@Param("rno") Long rno);

    int delete(@Param("rno") Long rno);

    int update(ReplyDTO replyDTO);
}
