package com.respond.jiaoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.respond.jiaoj.common.BaseResponse;
import com.respond.jiaoj.common.ErrorCode;
import com.respond.jiaoj.common.ResultUtils;
import com.respond.jiaoj.exception.BusinessException;
import com.respond.jiaoj.model.dto.question.QuestionQueryRequest;
import com.respond.jiaoj.model.dto.qusetionSubmit.QuestionSubmitAddRequest;
import com.respond.jiaoj.model.dto.qusetionSubmit.QuestionSubmitQueryRequest;
import com.respond.jiaoj.model.entity.Question;
import com.respond.jiaoj.model.entity.QuestionSubmit;
import com.respond.jiaoj.model.entity.User;
import com.respond.jiaoj.model.vo.QuestionSubmitVO;
import com.respond.jiaoj.service.QuestionSubmitService;
import com.respond.jiaoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
//@RequestMapping("/question_submit")
@Slf4j
@Deprecated
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     */
//    @PostMapping("/")
//    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//            HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionID() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        // 登录才能点赞
//        final User loginUser = userService.getLoginUser(request);
//        long questionID = questionSubmitAddRequest.getQuestionID();
//        long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(result);
//    }

    /**
     * 分页获取题目提交信息列表（除管理员外，普通用户只能看到非答案，提交代码外的公开信息）
     */
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest
//            , HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        // 从数据库获得原始的题目提交分页信息
//        Page<QuestionSubmit> questionPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        //返回脱敏信息
//        final User loginUser = userService.getLoginUser(request);
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionPage, loginUser));
//    }

}
