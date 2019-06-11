package com.pzy.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.pzy.study.base.commons.utils.IpAddressUtil;
import com.pzy.study.base.commons.utils.RequestHolder;
import com.pzy.study.base.commons.utils.Result;
import com.pzy.study.dao.AclDao;
import com.pzy.study.entity.AclEntity;
import com.pzy.study.entity.UserEntity;
import com.pzy.study.service.AclService;
import com.pzy.study.vo.AclVo;
import com.pzy.study.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-05-27
 */
@Service
public class AclServiceImpl implements AclService {

    @Autowired
    private AclDao aclDAO;

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
}
