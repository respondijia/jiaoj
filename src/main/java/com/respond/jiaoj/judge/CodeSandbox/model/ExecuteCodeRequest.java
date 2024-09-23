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
public class ExecuteCodeRequest {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户提交代码
     */
    private String code;

    /**
     * 判题用例
     */
    private List<String> inputList;
}
