package com.ayun.medical.cmn.service;

import com.ayun.medical.cmn.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> findChildDataById(Long id);


    //导出数据字典接口
    void exportDictData(HttpServletResponse response);

    //导入字典
    void importDictData(MultipartFile file);
}
