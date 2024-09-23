package com.respond.jiaoj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目提交信息枚举
 */
public enum JudgeInfoMessageEnum {

    Accepted("成功", "Succeed"),
    Wrong_Answer("答案错误", "Wrong Answer"),
    Compile_Error("编译错误", "Compile Error"),
    Memory_Limit_Exceeded("内存溢出", "Memory Limit Exceeded"),
    Time_Limit_Exceeded("超时", "Time Limit Exceeded"),
    Presentation_Error("展示错误", "Presentation Error"),
    Output_Limit_Exceeded("输出溢出", "Output Limit Exceeded"),
    Waiting("等待中", "Waiting"),
    Dangerous_Operation("危险操作", "Dangerous Operation"),
    Runtime_Error("运行错误", "Runtime Error"),
    System_Error("系统错误", "System Error");

    private final String text;

    private final String value;

    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
