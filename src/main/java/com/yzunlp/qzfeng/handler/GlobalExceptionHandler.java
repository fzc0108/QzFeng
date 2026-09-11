package com.yzunlp.qzfeng.handler;

import com.yzunlp.qzfeng.common.BaseException;
import com.yzunlp.qzfeng.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 1. 捕获自定义业务异常 (BaseException)
     */
    @ExceptionHandler(BaseException.class)
    public Result exceptionHandler(BaseException ex) {
        log.warn("业务异常：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 2. 捕获运行时异常 (RuntimeException)
     */
    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException ex) {
        // 使用 warn 级别，避免错误日志刷屏
        log.warn("业务拦截：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 3. 捕获 @Validated 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        // 这里的 getFieldError() 可能为空，加个判断更稳健
        String msg = bindingResult.getFieldError() != null ?
                bindingResult.getFieldError().getDefaultMessage() : "参数校验失败";
        return Result.error(msg);
    }

    /**
     * 4. 处理 SQL 唯一约束异常
     */
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, DuplicateKeyException.class})
    public Result handleSqlException(Exception ex) {
        // 获取异常的具体信息
        String msg = ex.getMessage();

        log.error("数据库唯一约束异常：{}", msg);

        if (msg != null) {

            if (msg.contains("idx_user_code") || msg.contains("user_code")) {
                return Result.error("该【用户编号】已存在，请重新输入！");
            }

            if (msg.contains("phone")) {
                return Result.error("该【手机号】已注册，请输入编号！");
            }

            if (msg.contains("Duplicate entry")) {
                return Result.error("提交的数据已存在（重复录入）！");
            }
        }
        return Result.error("数据库操作失败，数据冲突！");
    }


    /**
     * 忽略静态资源找不到的异常
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public void handleNoResourceFoundException(NoResourceFoundException ex) {
    }

    /**
     * 6. 捕获所有其他未知异常
     * 只有真正未知的 bug 才会走到这里
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception ex) {
        log.error("系统严重异常：", ex);
        return Result.error("系统繁忙，请稍后再试或联系管理员！");
    }
}