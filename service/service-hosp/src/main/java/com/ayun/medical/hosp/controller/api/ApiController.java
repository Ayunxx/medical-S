package com.ayun.medical.hosp.controller.api;


import com.ayun.medical.common.execption.MedicalException;
import com.ayun.medical.common.result.Result;
import com.ayun.medical.common.result.ResultCodeEnum;
import com.ayun.medical.helper.HttpRequestHelper;
import com.ayun.medical.hosp.service.HospitalService;
import com.ayun.medical.hosp.service.HospitalSetService;
import com.ayun.medical.utlis.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ApiController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSetService hospitalSetService;


    //上传医院接口
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){
        //获取医院传递过来的信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //获取医院签名
        String  hospSign = (String)paramMap.get("sign");
        //获取医院编码
        String hospCode = (String)paramMap.get("hoscode");
        String singKey = hospitalSetService.getSingKey(hospCode);
        //用MD5在加密
        String sinkeyMDd5 = MD5.encrypt(singKey);
        //判断签名是否一致
        if (!hospSign.equals(sinkeyMDd5)){
            throw new MedicalException(ResultCodeEnum.SIGN_ERROR);
        }
        //传输过程中+号会转为空格，因此需要转回来
        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        paramMap.put("logoData",logoData);
        //调用service方法
        hospitalService.save(paramMap);

        return Result.ok();
    }
}
