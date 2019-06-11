package com.pzy.study.dao;

import com.pzy.study.entity.DeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-19
 */
@Mapper
public interface DeptDao {

  DeptEntity selectDeptById(Integer id);

  void insertDept(DeptEntity deptEntity);

  List<DeptEntity> selectAllDept();

  void updateDeptById(DeptEntity deptEntity);

  List<DeptEntity> getChildDeptListByLevel(String level);

  void batchUpdateLevel(List<DeptEntity> list);

  int countByNameAndParentId(@Param("parentId")Integer parentId , @Param("name")String name , @Param("id")Integer id);
}
