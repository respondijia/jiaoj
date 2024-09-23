package com.respond.jiaoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.respond.jiaoj.model.dto.question.JudgeCase;
import com.respond.jiaoj.model.dto.question.JudgeConfig;
import com.respond.jiaoj.judge.CodeSandbox.model.JudgeInfo;
import com.respond.jiaoj.model.entity.Question;
import com.respond.jiaoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * 默认判题策略
 */
public class DefaultJudgeStrategy implements JudgeStrategy {
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();

        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.Accepted;

        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);
        // 判断输出用例和输入用例是不是一样多
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = judgeInfoMessageEnum.Wrong_Answer;
            return judgeInfoResponse;
        }
        // 判断输出是否和输出用例一样
        for (int i = 0; i < judgeCaseList.size(); i++) {
            JudgeCase judgeCase = judgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = judgeInfoMessageEnum.Wrong_Answer;
                return judgeInfoResponse;
            }
        }

        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        Long memoryLimit = judgeConfig.getMemoryLimit();
        Long timeLimit = judgeConfig.getTimeLimit();
        if (memory > memoryLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.Memory_Limit_Exceeded;
            return judgeInfoResponse;
        }
        if (time > timeLimit) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.Time_Limit_Exceeded;
            return judgeInfoResponse;
        }
        return judgeInfoResponse;
    }
}
