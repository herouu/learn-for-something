package com.github.herouu.trainhigh.controller;

import java.util.List;
import java.util.Map;

import com.github.herouu.trainhigh.common.domian.KdsChapterPoint;
import com.github.herouu.trainhigh.common.vo.BaseResponse;
import com.github.herouu.trainhigh.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
