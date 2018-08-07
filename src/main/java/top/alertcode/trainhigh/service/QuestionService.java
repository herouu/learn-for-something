package top.alertcode.trainhigh.service;

import java.util.List;
import top.alertcode.trainhigh.common.domian.KdsChapterPoint;

/**
 * Question service接口.
 *
 * @author Z佬
 * @version v1.0
 * @date 20180807 9:11:37
 */
public interface QuestionService {

  /**
   * Insert sql question.
   */
  void insertSqlQuestion();

  /**
   * Update sql question.
   */
  void updateSqlQuestion();

  /**
   * Delete sql question.
   */
  void deleteSqlQuestion();

  /**
   * Query sql question list.
   *
   * @return list
   */
  List<KdsChapterPoint> querySqlQuestion(long id);

  /**
   * Insert redis question.
   */
  void insertRedisQuestion();

  /**
   * Update redis question.
   */
  void updateRedisQuestion();

  /**
   * Delete redis question.
   */
  void deleteRedisQuestion();

  /**
   * Query redis question list.
   *
   * @return list
   */
  List<KdsChapterPoint> queryRedisQuestion();

}
