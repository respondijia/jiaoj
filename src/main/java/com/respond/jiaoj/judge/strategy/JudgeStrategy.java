package com.respond.jiaoj.judge.strategy;

import com.respond.jiaoj.judge.CodeSandbox.model.JudgeInfo;

/**
 * 判题策略
 */

public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}
