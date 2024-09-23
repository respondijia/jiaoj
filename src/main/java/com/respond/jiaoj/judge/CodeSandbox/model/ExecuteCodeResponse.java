package com.respond.jiaoj.judge.CodeSandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteCodeResponse {

    /**
     * 判题输出
     */
    private List<String> outputList;

    /**
     * 判题状态
     */
    private Integer status;

    /**
     * 接口信息
     */
    private String message;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
