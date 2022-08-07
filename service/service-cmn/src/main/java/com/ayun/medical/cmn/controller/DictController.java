package com.ayun.medical.cmn.controller;


import com.ayun.medical.cmn.entity.Dict;
import com.ayun.medical.cmn.service.DictService;
import com.ayun.medical.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(value = "数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin    //跨域问题
public class DictController {

    @Autowired
    DictService dictService;


    //导入数据字典
    @PostMapping("importDict")
    public Result importDict(MultipartFile file){
        dictService.importDictData();
        return Result.ok();
    }

    //导出数据字典接口
    @GetMapping("exportDictData")
    public void exportDict(HttpServletResponse response){
        dictService.exportDictData(response);
    }

    @ApiOperation(value = "根据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
        List<Dict> list = dictService.findChildDataById(id);
        return Result.ok(list);
    }

}
