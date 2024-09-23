package com.respond.jiaoj.judge.CodeSandbox;

import com.respond.jiaoj.judge.CodeSandbox.impl.ExampleCodeSandbox;
import com.respond.jiaoj.judge.CodeSandbox.impl.RemoteCodeSandbox;
import com.respond.jiaoj.judge.CodeSandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂（根据传入的字符串参数自动创建对应的代码沙箱实例）
 */
public class CodeSandboxFactory {

    /**
     * 创建代码沙箱示例
     * @param type
     * @return
     */
    public static CodeSandbox newInstance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
