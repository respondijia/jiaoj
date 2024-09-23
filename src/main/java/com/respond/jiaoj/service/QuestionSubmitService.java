package com.respond.jiaoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.respond.jiaoj.model.dto.question.QuestionQueryRequest;
import com.respond.jiaoj.model.dto.qusetionSubmit.QuestionSubmitAddRequest;
import com.respond.jiaoj.model.dto.qusetionSubmit.QuestionSubmitQueryRequest;
import com.respond.jiaoj.model.entity.Question;
import com.respond.jiaoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.respond.jiaoj.model.entity.User;
import com.respond.jiaoj.model.vo.QuestionSubmitVO;
import com.respond.jiaoj.model.vo.QuestionVO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
* @author 15242
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-08-25 22:28:29
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
