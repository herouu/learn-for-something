package top.alertcode.trainhigh.controller;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alertcode.trainhigh.common.domian.KdsChapterPoint;
import top.alertcode.trainhigh.common.vo.BaseResponse;
import top.alertcode.trainhigh.service.QuestionService;

/**
 * @author alertcode
 * @date 2018-08-12
 * @copyright alertcode.top
 */
@RestController
@Slf4j
public class QuestionController {


  @Autowired
  private QuestionService questionService;

  @GetMapping("querySqlQuestion")
  public BaseResponse querySqlQuestion(int id) {
    List<KdsChapterPoint> kdsChapterPoints = questionService.querySqlQuestion(id);
//    log.info(JSON.toJSONString(kdsChapterPoints));
    return BaseResponse.success(kdsChapterPoints);
  }

  @GetMapping("queryRedisQuestion")
  public BaseResponse queryRedisQuestion(int id) {
    Map map = questionService.queryRedisQuestion(id);
//    log.info(JSON.toJSONString(kdsChapterPoints));
    return BaseResponse.success(map);
  }
}
