package com.pzy.study.service.impl;

import com.google.common.collect.Lists;
import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.LevelUtil;
import com.pzy.study.dao.DeptDao;
import com.pzy.study.entity.DeptEntity;
import com.pzy.study.service.DeptService;
import com.pzy.study.vo.DeptVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-23
 */
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDAO;

    @Override
    public void saveDept(DeptVo deptVO) {
        DeptEntity build = DeptEntity.builder().
                name(deptVO.getName()).
                parentId(deptVO.getParentId()).
                seq(deptVO.getSeq()).
                remark(deptVO.getRemark()).
                build();
        build.setLevel(LevelUtil.calculateLevel(getLevel(deptVO.getParentId()) , deptVO.getParentId()));
        build.setOperator("admin");
        build.setOperatorIp("127.0.0.1");
        deptDAO.insertDept(build);
    }


    public String getLevel(Integer deptId){
        DeptEntity entity = deptDAO.selectDeptById(deptId);
        if (null == entity){
            return null;
        }else {
            return entity.getLevel();
        }
    }

    @Override
    public List<DeptEntity> deptTree() {
        List<DeptEntity> deptList = deptDAO.selectAllDept();
        return deptListToTree(deptList);
    }

    private List<DeptEntity> deptListToTree(List<DeptEntity> deptList){
        if (deptList.isEmpty()){
            return Lists.newArrayList();
        }
        Map<String, List<DeptEntity>> levelDeptMap = deptList.stream().collect(Collectors.groupingBy(deptEntity -> deptEntity.getLevel()));
        List<DeptEntity> rootList = deptList.stream().
                filter(entity -> LevelUtil.ROOT.equals(entity.getLevel())).
                sorted(new Comparator<DeptEntity>() {
                    @Override
                    public int compare(DeptEntity o1, DeptEntity o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                }).collect(Collectors.toList());
        transFormDeptTree(rootList ,LevelUtil.ROOT ,levelDeptMap);
        return rootList;
    }

    public void transFormDeptTree(List<DeptEntity> rootList , String level , Map<String, List<DeptEntity>> levelDeptMap ){
        for (DeptEntity entity : rootList) {
            String nextLevel = LevelUtil.calculateLevel(level, entity.getId());
            List<DeptEntity> list = levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(list)) {
                list.sort(new Comparator<DeptEntity>() {
                    @Override
                    public int compare(DeptEntity o1, DeptEntity o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
                //设置当前层级下一层级list
                entity.setDeptList(list);
                //遍历下一层级
                transFormDeptTree(list , nextLevel ,levelDeptMap);
            }
        }
    }

    @Override
    @Transactional
    public void updateDept(DeptVo deptVO) {
        deptVO.setParentId(deptVO.getParentId() == null ? 0 : deptVO.getParentId());
        if (checkExist(deptVO.getParentId() , deptVO.getName() , deptVO.getId())){
            throw new WebBaseException("同一层级下存在相同名称的部门" , WebBaseExceptionEnum.request_prarms);
        }
        DeptEntity before = deptDAO.selectDeptById(deptVO.getId());
        if (null == before){throw new WebBaseException("待更新的部门不存在" , WebBaseExceptionEnum.request_prarms);}
        DeptEntity after = DeptEntity.builder().
                id(deptVO.getId()).
                name(deptVO.getName()).
                parentId(deptVO.getParentId()).
                seq(deptVO.getSeq()).
                remark(deptVO.getRemark()).
                build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(deptVO.getParentId()), deptVO.getParentId()));
        after.setOperator("admin"); //todo
        after.setOperatorIp("127.0.0.1");
        updateWithChild(before ,after);
    }

    public void updateWithChild(DeptEntity before , DeptEntity after){
        deptDAO.updateDeptById(after);
        String newLevelPrefix = after.getLevel();
        String oldLevelProfix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())){
            List<DeptEntity> childDeptList = deptDAO.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(childDeptList)){
                for (DeptEntity entity : childDeptList) {
                    String level = entity.getLevel();
                    if (level.indexOf(oldLevelProfix)==0){
                        level = newLevelPrefix + level.substring(oldLevelProfix.length());
                        entity.setLevel(level);
                    }
                }
                deptDAO.batchUpdateLevel(childDeptList);
            }
        }
    }

    private boolean checkExist(Integer parentId , String deptName , Integer deptId){
        return deptDAO.countByNameAndParentId(parentId , deptName ,deptId) > 0;
    }
}
