package com.respond.jiaoj.judge;

import com.respond.jiaoj.judge.strategy.DefaultJudgeStrategy;
import com.respond.jiaoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.respond.jiaoj.judge.strategy.JudgeContext;
import com.respond.jiaoj.judge.strategy.JudgeStrategy;
import com.respond.jiaoj.judge.CodeSandbox.model.JudgeInfo;
import com.respond.jiaoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 简化调用
 */
@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (language.equals("java")){
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
