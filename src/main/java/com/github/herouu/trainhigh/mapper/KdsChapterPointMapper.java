package com.github.herouu.trainhigh.mapper;

import java.util.List;
import java.util.Map;

import com.github.herouu.trainhigh.common.domian.KdsChapterPoint;
import com.github.herouu.trainhigh.common.domian.KdsChapterPointExample;
import org.apache.ibatis.annotations.Param;

public interface KdsChapterPointMapper {

  int countByExample(KdsChapterPointExample example);

  int deleteByExample(KdsChapterPointExample example);

  int deleteByPrimaryKey(Long id);

  int insert(KdsChapterPoint record);

  int insertSelective(KdsChapterPoint record);

  List<KdsChapterPoint> selectByExampleWithBLOBs(KdsChapterPointExample example);

  List<Map<Object,Object>> selectByExample(KdsChapterPointExample example);

  KdsChapterPoint selectByPrimaryKey(Long id);

  int updateByExampleSelective(@Param("record") KdsChapterPoint record,
      @Param("example") KdsChapterPointExample example);

  int updateByExampleWithBLOBs(@Param("record") KdsChapterPoint record,
      @Param("example") KdsChapterPointExample example);

  int updateByExample(@Param("record") KdsChapterPoint record,
      @Param("example") KdsChapterPointExample example);

  int updateByPrimaryKeySelective(KdsChapterPoint record);

  int updateByPrimaryKeyWithBLOBs(KdsChapterPoint record);

  int updateByPrimaryKey(KdsChapterPoint record);
}
