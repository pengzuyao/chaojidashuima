package com.pzy.study.dao;

import com.pzy.study.entity.RoleEntity;
import com.pzy.study.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Mapper
public interface RoleDao {

    void insertRole(RoleEntity roleEntity);

    void updateRole(RoleEntity roleEntity);

    List<RoleEntity> getAll();


}
