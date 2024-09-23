package com.respond.jiaoj.judge.CodeSandbox;

import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeRequest;
import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
