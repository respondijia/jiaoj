package com.respond.jiaoj.judge.CodeSandbox.model;

import lombok.Data;

/**
 * @Description: 题目提交信息
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 时间限制(ms)
     */
    private Long time;

    /**
     * 内存限制(kb)
     */
    private Long memory;
}
