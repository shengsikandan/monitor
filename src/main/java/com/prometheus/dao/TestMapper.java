package com.prometheus.dao;

import com.prometheus.Entry.TestEntry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {
    @Select("SELECT * FROM test WHERE id = #{id}")
    TestEntry findById(@Param("id") int id);
}
