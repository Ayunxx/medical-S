package com.ayun.medical.hosp.service.impl;

import com.ayun.medical.common.result.Result;
import com.ayun.medical.hosp.dao.HospitalSetDao;
import com.ayun.medical.hosp.service.HospitalSetService;
import com.ayun.medical.model.hosp.HospitalSet;
import com.ayun.medical.utlis.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetDao, HospitalSet> implements HospitalSetService {

    @Autowired
    private HospitalSetDao hospitalSetDao;

}
