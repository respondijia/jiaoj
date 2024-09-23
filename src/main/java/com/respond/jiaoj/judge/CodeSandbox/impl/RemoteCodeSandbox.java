package com.respond.jiaoj.judge.CodeSandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.respond.jiaoj.common.ErrorCode;
import com.respond.jiaoj.exception.BusinessException;
import com.respond.jiaoj.judge.CodeSandbox.CodeSandbox;
import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeRequest;
import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {

    // 安全管理
    public static final String AUTH_REQUEST_HEADER = "auth";

    public static final String AUTH_REQUEST_HEADER_VALUE = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8090/execCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String body = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,AUTH_REQUEST_HEADER_VALUE)
                .body(json)
                .execute()
                .body();
        if (StrUtil.isBlank(body)){
            throw new BusinessException(ErrorCode.ExecCode_ERROR,"RemoteCodeSandbox error :" + body);
        }
        return JSONUtil.toBean(body, ExecuteCodeResponse.class);
    }
}
