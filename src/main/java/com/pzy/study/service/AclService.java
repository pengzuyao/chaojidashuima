package com.pzy.study.service;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.entity.AclEntity;
import com.pzy.study.vo.AclVo;
import com.pzy.study.vo.UserVo;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-27
 */
public interface AclService {

    void saveAcl(AclVo aclVO);

    void update(AclVo aclVO);

    Result pageQueryAcl(AclVo aclVO);

    void changeRoleAcls(Integer roleId , String aclIds);

    List<AclEntity> findAclsByRoleIds(List<Integer> roleIds);
}
