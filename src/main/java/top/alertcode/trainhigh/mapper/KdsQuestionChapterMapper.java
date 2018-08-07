package top.alertcode.trainhigh.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.alertcode.trainhigh.common.domian.KdsQuestionChapter;
import top.alertcode.trainhigh.common.domian.KdsQuestionChapterExample;

public interface KdsQuestionChapterMapper {

  int countByExample(KdsQuestionChapterExample example);

  int deleteByExample(KdsQuestionChapterExample example);

  int deleteByPrimaryKey(Long id);

  int insert(KdsQuestionChapter record);

  int insertSelective(KdsQuestionChapter record);

  List<KdsQuestionChapter> selectByExample(KdsQuestionChapterExample example);

  KdsQuestionChapter selectByPrimaryKey(Long id);

  int updateByExampleSelective(@Param("record") KdsQuestionChapter record,
      @Param("example") KdsQuestionChapterExample example);

  int updateByExample(@Param("record") KdsQuestionChapter record,
      @Param("example") KdsQuestionChapterExample example);

  int updateByPrimaryKeySelective(KdsQuestionChapter record);

  int updateByPrimaryKey(KdsQuestionChapter record);
}