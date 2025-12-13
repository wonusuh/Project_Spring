package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface TestMapper {
    @Insert("INSERT INTO tbl_testA (col1) values (#{str})")
    int insertA(@Param("str") String str);

    @Insert("INSERT INTO tbl_testB (col2) values (#{str})")
    int insertB(@Param("str") String str);
}
