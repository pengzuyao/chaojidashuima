package com.pzy.study.service.impl;

import com.google.common.collect.Lists;
import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.IpAddressUtil;
import com.pzy.study.base.commons.utils.LevelUtil;
import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.dao.AclModuleDao;
import com.pzy.study.entity.AclModuleEntity;
import com.pzy.study.entity.AclModuleLevelEntity;
import com.pzy.study.service.AclModuleService;
import com.pzy.study.vo.AclModuleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-26
 */
@Service
@Slf4j
public class AclModuleServiceImpl implements AclModuleService {

    @Autowired
    private AclModuleDao aclModuleDAO;

    @Override
    public void saveAclModule(AclModuleVo aclModuleVO) {
        AclModuleEntity build = AclModuleEntity.builder().
                name(aclModuleVO.getName()).
                parentId(aclModuleVO.getParentId()).
                seq(aclModuleVO.getSeq()).
                level(LevelUtil.calculateLevel(getLevel(aclModuleVO.getParentId()) , aclModuleVO.getParentId())).
                status(aclModuleVO.getStatus()).
                remark(aclModuleVO.getRemark()).
                operator("admin").
                operatorIp("127.0.0.1").
                build();
                aclModuleDAO.saveAclModule(build);
    }

    public String getLevel(Integer aclModuleId){
        AclModuleEntity entity = aclModuleDAO.selectAclModuleById(aclModuleId);
        if (null == entity){
            return null;
        }else {
            return entity.getLevel();
        }
    }

    @Override
    public List<AclModuleLevelEntity> aclModuleTree() {
        List<AclModuleEntity> aclModuleList= aclModuleDAO.selectAllAclModule();
        List<AclModuleLevelEntity> aclModuleLevelEntities = Lists.newArrayList();
        aclModuleList.forEach(aclModuleEntity -> {
            AclModuleLevelEntity adapt = AclModuleLevelEntity.adapt(aclModuleEntity);
            aclModuleLevelEntities.add(adapt);
        });
        return deptListToTree(aclModuleLevelEntities);
    }

    private List<AclModuleLevelEntity> deptListToTree(List<AclModuleLevelEntity> aclModuleList){
        if (aclModuleList.isEmpty()){
            return Lists.newArrayList();
        }
        Map<String, List<AclModuleLevelEntity>> levelDeptMap = aclModuleList.stream().collect(Collectors.groupingBy(aclModuleEntity -> aclModuleEntity.getLevel()));
        List<AclModuleLevelEntity> rootList = aclModuleList.stream().
                filter(entity -> LevelUtil.ROOT.equals(entity.getLevel())).
                sorted(new Comparator<AclModuleEntity>() {
                    @Override
                    public int compare(AclModuleEntity o1, AclModuleEntity o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                }).collect(Collectors.toList());
        transFormDeptTree(rootList ,LevelUtil.ROOT ,levelDeptMap);
        return rootList;
    }

    public void transFormDeptTree(List<AclModuleLevelEntity> rootList , String level , Map<String, List<AclModuleLevelEntity>> levelDeptMap ){
        for (AclModuleLevelEntity entity : rootList) {
            String nextLevel = LevelUtil.calculateLevel(level, entity.getId());
            List<AclModuleLevelEntity> list = levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(list)) {
                list.sort(new Comparator<AclModuleEntity>() {
                    @Override
                    public int compare(AclModuleEntity o1, AclModuleEntity o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
                //设置当前层级下一层级list
                entity.setAclModuleLevelList(list);
                //遍历下一层级
                transFormDeptTree(list , nextLevel ,levelDeptMap);
            }
        }
    }

    @Override
    @Transactional
    public void updateaclModule(AclModuleVo aclModuleVO) {
        aclModuleVO.setParentId(aclModuleVO.getParentId() == null ? 0 : aclModuleVO.getParentId());
        if (checkExist(aclModuleVO.getParentId() , aclModuleVO.getName() , aclModuleVO.getId())){
            throw new WebBaseException("同一层级下存在相同名称的权限" , WebBaseExceptionEnum.request_prarms);
        }
        AclModuleEntity before = aclModuleDAO.selectAclModuleById(aclModuleVO.getId());
        if (before == null ){throw new WebBaseException("待更新的权限模块不存在" , WebBaseExceptionEnum.request_prarms);}
        AclModuleEntity after = AclModuleEntity.builder().
                id(aclModuleVO.getId()).
                name(aclModuleVO.getName()).
                parentId(aclModuleVO.getParentId()).
                seq(aclModuleVO.getSeq()).
                level(LevelUtil.calculateLevel(getLevel(aclModuleVO.getParentId()) , aclModuleVO.getParentId())).
                status(aclModuleVO.getStatus()).
                remark(aclModuleVO.getRemark()).
                operator("admin").
                operatorIp("127.0.0.1").
                build();
        updateWithChild(before , after);
    }

    public void updateWithChild(AclModuleEntity before , AclModuleEntity after){
         aclModuleDAO.updateAclModule(after);
        String newLevelPrefix = after.getLevel();
        String oldLevelProfix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())){
            List<AclModuleEntity> childDeptList = aclModuleDAO.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(childDeptList)){
                for (AclModuleEntity entity : childDeptList) {
                    String level = entity.getLevel();
                    if (level.indexOf(oldLevelProfix)==0){
                        level = newLevelPrefix + level.substring(oldLevelProfix.length());
                        entity.setLevel(level);
                    }
                }
                aclModuleDAO.batchUpdateLevel(childDeptList);
            }
        }
    }

    private boolean checkExist(Integer parentId , String aclModuleName , Integer aclModuleId){
        return aclModuleDAO.countByNameAndParentId(parentId , aclModuleName ,aclModuleId) > 0;
    }
}
