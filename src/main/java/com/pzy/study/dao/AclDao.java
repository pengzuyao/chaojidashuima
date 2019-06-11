package com.pzy.study.dao;

import com.pzy.study.entity.AclEntity;
import com.pzy.study.entity.RoleAclRelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Mapper
public interface AclDao {

    void saveAcl(AclEntity aclEntity);

    Integer getNextCode();

    void updateAcl(AclEntity aclEntity);

    List<AclEntity> selectAll();

    List<AclEntity> selectAclsByRoles(@Param("roleIds") List<Integer> roleIds);

    List<AclEntity> selectAclsByAclModuleId(Integer aclModuleId);

}
