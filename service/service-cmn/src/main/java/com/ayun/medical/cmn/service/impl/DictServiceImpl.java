package com.ayun.medical.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ayun.medical.cmn.dao.DictDao;
import com.ayun.medical.cmn.entity.Dict;
import com.ayun.medical.cmn.service.DictService;
import com.ayun.medical.cmn.vo.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {

    @Override
    public List<Dict> findChildDataById(Long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dict> dictList =baseMapper.selectList(wrapper);
        //想list集合中每一个dict对象中设置haschildren
        for (Dict dict : dictList){
            Long dictId =  dict.getId();
            boolean isChild = this.isChildren(dictId);
            dict.setHasChildren(isChild);
        }

        return dictList;
    }

    @Override
    public void exportDictData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "dict";
        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");

        //查询数据库
        List<Dict> dictList = baseMapper.selectList(null);
        ArrayList<DictEeVo> dictVoList = new ArrayList<>();

        for (Dict dict : dictList) {
            DictEeVo dictEeVo = new DictEeVo();
            BeanUtils.copyProperties(dict,dictEeVo);
            dictVoList.add(dictEeVo);
        }

        //调用方法执行写操作
        try {
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet()
                    .doWrite(dictVoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void importDictData() {

    }

    //判断id下是否有子节点
    private boolean isChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Long count = baseMapper.selectCount(wrapper);
        return count > 0;
    }
}
