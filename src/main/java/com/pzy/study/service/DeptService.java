package com.pzy.study.service;

import com.pzy.study.entity.DeptEntity;
import com.pzy.study.vo.DeptVo;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-23
 */
public interface DeptService {

    public  void saveDept(DeptVo deptVO);

    public List<DeptEntity> getDeptTree();

    public void updateDept(DeptVo deptVO);

}
