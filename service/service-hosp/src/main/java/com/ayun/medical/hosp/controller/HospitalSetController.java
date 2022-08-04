package com.ayun.medical.hosp.controller;


import com.ayun.medical.hosp.service.HospitalSetService;
import com.ayun.medical.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    //查询医院设置所有信息
    @GetMapping("/findAll")
    public List<HospitalSet> findallHospitalSet(){
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }

    //删除医院设置
    @DeleteMapping("{id}")
    public boolean removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        return flag;
    }
}
