package com.github.herouu.trainhigh.design.factory.impl;

import com.github.herouu.trainhigh.design.factory.AbstractHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AHandleImpl extends AbstractHandle {
    @Override
    public void handler() {
      log.info("AHandleImpl");
    }
}
