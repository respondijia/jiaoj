package com.respond.jiaoj.model.dto.qusetionSubmit;


import com.respond.jiaoj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 创建请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 题目 id
     */
    private Long questionID;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 提交状态
     */
    private Integer status;

    /**
     * 编程语言
     */
    private String language;
}