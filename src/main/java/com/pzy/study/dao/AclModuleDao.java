package com.pzy.study.dao;

import com.pzy.study.entity.AclModuleEntity;
import com.pzy.study.entity.AclModuleLevelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Mapper
public interface AclModuleDao {

        public void saveAclModule(AclModuleEntity aclModuleEntity);

        public void updateAclModule(AclModuleEntity aclModuleEntity);

        int countByNameAndParentId(@Param("parentId")Integer parentId , @Param("name")String name , @Param("id")Integer id);

        AclModuleEntity selectAclModuleById(Integer id);

        List<AclModuleEntity> getChildDeptListByLevel(String level);

        void batchUpdateLevel(List<AclModuleEntity> list);

        List<AclModuleEntity> selectAllAclModule();
}
