package com.pzy.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.pzy.study.base.commons.enums.WebBaseExceptionEnum;
import com.pzy.study.base.commons.exceptions.WebBaseException;
import com.pzy.study.base.commons.utils.IpAddressUtil;
import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.dao.AclDao;
import com.pzy.study.dao.RoleAclRelDao;
import com.pzy.study.entity.AclEntity;
import com.pzy.study.entity.RoleAclRelEntity;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.service.AclService;
import com.pzy.study.vo.AclVo;
import com.pzy.study.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-27
 */
@Service
public class AclServiceImpl implements AclService {

    @Autowired
    private AclDao aclDAO;

    @Autowired
    private RoleAclRelDao roleAclRelDao;

    @Override
    public void saveAcl(AclVo aclVO) {
        Integer nextCode = aclDAO.getNextCode();
        AclEntity build = AclEntity.builder().
                aclModuleId(aclVO.getAclModuleId()).
                code(nextCode).
                name(aclVO.getName()).
                url(aclVO.getUrl()).
                type(aclVO.getType()).
                seq(aclVO.getSeq()).
                status(aclVO.getStatus()).
                remark(aclVO.getRemark()).
                operator("admin").
                operatorIp("127.0.0.1").
                build();
            aclDAO.saveAcl(build);

    }

    @Override
    public void update(AclVo aclVO) {
        AclEntity build = AclEntity.builder().
                id(aclVO.getId()).
                aclModuleId(aclVO.getAclModuleId()).
                code(aclVO.getCode()).
                url(aclVO.getUrl()).
                type(aclVO.getType()).
                name(aclVO.getName()).
                seq(aclVO.getSeq()).
                status(aclVO.getStatus()).
                remark(aclVO.getRemark()).
                operator("admin").
                operatorIp("127.0.0.1").
                build();
        aclDAO.updateAcl(build);
    }

    @Override
    public Result userPageQuery(AclVo aclVO) {
        PageHelper.startPage(aclVO.getPageNo() , aclVO.getPageSize());
        List<AclEntity> aclEntities = Lists.newArrayList();
        aclEntities = aclDAO.selectAclsByAclModuleId(aclVO.getAclModuleId());
        return Result.success().set(Result.DATA_KEY ,new PageInfo<AclEntity>(aclEntities));
    }

    @Transactional
    @Override
    public void changeRoleAcls(Integer roleId, String aclIds) {
            aclDAO.deleteRoleAclRelByRoleId(roleId);
            if (StringUtils.isBlank(aclIds)){
                return;
            }
            List<Integer> aclList = Lists.newArrayList();
            stringToIntegerList(aclIds ,aclList);
            List<RoleAclRelEntity> list = Lists.newArrayList();
            for (Integer aclId : aclList) {
                RoleAclRelEntity build = RoleAclRelEntity.builder().
                        roleId(roleId).
                        aclId(aclId).
                        operator(RequestHolder.getCurrentUser().getUsername()).
                        operatorIp(IpAddressUtil.getIpAdrress(RequestHolder.getCurrentRequest())).
                        build();
                list.add(build);
            }
        roleAclRelDao.batchInsert(list);
    }

    private void stringToIntegerList(String aclIds, List<Integer> aclList) {
        try {
            String[] split = aclIds.split(",");
            for (int i = 0; i < split.length; i++) {
                aclList.add(Integer.valueOf(split[i]));
            }
        }catch (Exception e){
            throw  new WebBaseException("权限字符串转换异常" , WebBaseExceptionEnum.request_prarms);
        }

    }

}
