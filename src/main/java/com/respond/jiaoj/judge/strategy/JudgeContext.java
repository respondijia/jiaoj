package com.respond.jiaoj.judge.strategy;


import com.respond.jiaoj.model.dto.question.JudgeCase;
import com.respond.jiaoj.judge.CodeSandbox.model.JudgeInfo;
import com.respond.jiaoj.model.entity.Question;
import com.respond.jiaoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于判题策略传递的参数）
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}
