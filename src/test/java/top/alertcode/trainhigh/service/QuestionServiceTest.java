package top.alertcode.trainhigh.service;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.alertcode.trainhigh.TrainHighApplicationTests;
import top.alertcode.trainhigh.common.domian.KdsChapterPoint;

public class QuestionServiceTest extends TrainHighApplicationTests {

  @Autowired
  private QuestionService questionService;

  @Test
  public void insertSqlQuestion() {
  }

  @Test
  public void updateSqlQuestion() {
  }

  @Test
  public void deleteSqlQuestion() {
  }

  @Test
  public void querySqlQuestion() {
    List<KdsChapterPoint> kdsQuestionChapters = questionService.querySqlQuestion(329504L);
    System.out.println(kdsQuestionChapters.size());
  }

  @Test
  public void insertRedisQuestion() {
  }

  @Test
  public void updateRedisQuestion() {
  }

  @Test
  public void deleteRedisQuestion() {
  }

  @Test
  public void queryRedisQuestion() {
  }
}