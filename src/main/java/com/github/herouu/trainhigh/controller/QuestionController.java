package com.github.herouu.trainhigh.controller;

import java.util.List;
import java.util.Map;

import com.github.herouu.trainhigh.common.domian.KdsChapterPoint;
import com.github.herouu.trainhigh.common.vo.BaseResponse;
import com.github.herouu.trainhigh.design.factory.HandleFactory;
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
    HandleFactory handleFactory;

    @GetMapping(path = "/test")
    public void test() {
        handleFactory.getHandler("AHandleImpl").handler();
    }
}
