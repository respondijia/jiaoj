package com.respond.jiaoj.judge;

import cn.hutool.json.JSONUtil;
import com.respond.jiaoj.common.ErrorCode;
import com.respond.jiaoj.exception.BusinessException;
import com.respond.jiaoj.judge.CodeSandbox.CodeSandbox;
import com.respond.jiaoj.judge.CodeSandbox.CodeSandboxFactory;
import com.respond.jiaoj.judge.CodeSandbox.CodeSandboxProxy;
import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeRequest;
import com.respond.jiaoj.judge.CodeSandbox.model.ExecuteCodeResponse;
import com.respond.jiaoj.judge.strategy.DefaultJudgeStrategy;
import com.respond.jiaoj.judge.strategy.JudgeContext;
import com.respond.jiaoj.judge.strategy.JudgeStrategy;
import com.respond.jiaoj.model.dto.question.JudgeCase;
import com.respond.jiaoj.judge.CodeSandbox.model.JudgeInfo;
import com.respond.jiaoj.model.entity.Question;
import com.respond.jiaoj.model.entity.QuestionSubmit;
import com.respond.jiaoj.model.enums.QuestionSubmitStatusEnum;
import com.respond.jiaoj.service.QuestionService;
import com.respond.jiaoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codeSandbox.type}")
    private String type;

    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        // 1.根据题目id查询题目信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"题目不存在");
        }
        // 判断题目提交状态
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"题目正在判题中");
        }
        // 修改题目提交状态为判题中
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        // 更新题目提交信息（题目判题状态）
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新题目提交信息失败");
        }
        // 2.调用代码沙箱，获得代码执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .language(language)
                .code(code)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 3.根据执行结果，设置题目代码执行信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);

        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);

        //修改数据库中的信息
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"更新题目信息失败");
        }
        QuestionSubmit questionSubmitResult = questionSubmitService.getById(questionSubmitId);
        return questionSubmitResult;
    }
}
