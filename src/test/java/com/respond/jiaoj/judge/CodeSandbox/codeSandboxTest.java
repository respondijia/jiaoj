package com.respond.jiaoj.judge.CodeSandbox;

import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeRequest;
import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class codeSandboxTest {

    @Value("${codeSandbox.type}")
    String type;

    @Test
    void executeCodeByProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setLanguage("java");
        executeCodeRequest.setCode("public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"(a + b) = \" + (a + b));\n" +
                "    }\n" +
                "}");
        List<String> inputList = Arrays.asList("1 2","3 4");
        executeCodeRequest.setInputList(inputList);
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
    }
}