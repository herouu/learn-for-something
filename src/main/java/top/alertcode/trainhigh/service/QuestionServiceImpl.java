package top.alertcode.trainhigh.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alertcode.trainhigh.common.domian.KdsQuestionChapter;
import top.alertcode.trainhigh.mapper.KdsQuestionChapterMapper;

/**
 * Question service类.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180807 9:11:22
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

  @Autowired
  private KdsQuestionChapterMapper kdsQuestionChapterMapper;

  @Override
  public void insertSqlQuestion() {
  }

  @Override
  public void updateSqlQuestion() {

  }

  @Override
  public void deleteSqlQuestion() {

  }

  @Override
  public List<KdsQuestionChapter> querySqlQuestion(long id) {
    ArrayList<KdsQuestionChapter> objects = new ArrayList<>();
    KdsQuestionChapter kdsQuestionChapter = kdsQuestionChapterMapper.selectByPrimaryKey(id);
    objects.add(kdsQuestionChapter);
    return objects;
  }

  @Override
  public void insertRedisQuestion() {

  }

  @Override
  public void updateRedisQuestion() {

  }

  @Override
  public void deleteRedisQuestion() {

  }

  @Override
  public List<KdsQuestionChapter> queryRedisQuestion() {
    return null;
  }
}
