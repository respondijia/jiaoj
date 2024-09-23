package com.respond.jiaoj.judge;

import com.respond.jiaoj.model.entity.QuestionSubmit;

public interface JudgeService {

    /**
     * 判题服务
     * @return
     */
    QuestionSubmit doJudge(Long questionSubmitId);
}
