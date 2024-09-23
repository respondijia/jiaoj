package com.respond.jiaoj.model.dto.qusetionSubmit;


import lombok.Data;
import java.io.Serializable;


/**
 * 创建请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 题目 id
     */
    private Long questionID;

    /**
     * 代码
     */
    private String code;

    /**
     * 编程语言
     */
    private String language;
}