package com.ayun.medical.hosp.repository;

import com.ayun.medical.hosp.entity.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HospitalRepository extends MongoRepository<Hospital,String> {

    //判断是否存在该数据
    Hospital getHospitalByHoscode(String hoscode);
}
