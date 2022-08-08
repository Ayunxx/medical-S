package com.ayun.medical.listenner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ayun.medical.cmn.dao.DictDao;
import com.ayun.medical.cmn.entity.Dict;
import com.ayun.medical.cmn.vo.DictEeVo;
import org.springframework.beans.BeanUtils;

public class DictListener extends AnalysisEventListener<DictEeVo> {


    private DictDao dictDao;

    public DictListener(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    //一行一行读取从第二行开始读
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        //调用方法添加到数据库
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictDao.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
