package com.ayun.medical.hosp.service.impl;


import com.ayun.medical.hosp.dao.HospitalSetDao;

import com.ayun.medical.hosp.entity.HospitalSet;
import com.ayun.medical.hosp.service.HospitalSetService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetDao, HospitalSet> implements HospitalSetService {


    @Override
    public String getSingKey(String hospCode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hospCode);
        HospitalSet selectOne = baseMapper.selectOne(wrapper);

        return selectOne.getSignKey();
    }
}
