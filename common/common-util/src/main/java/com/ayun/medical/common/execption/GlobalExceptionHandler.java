package com.ayun.medical.common.execption;


import com.ayun.medical.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice

public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }
    @ExceptionHandler(MedicalException.class)
    @ResponseBody
    public Result error(MedicalException e){
        e.printStackTrace();
        return Result.build(e.getCode(),e.getMessage());
    }
}
