package com.jc.crm.service.department.impl;

import com.jc.crm.config.ResultStatus;
import com.jc.crm.form.department.DepartmentCreateForm;
import com.jc.crm.form.department.DepartmentUpdateForm;
import com.jc.crm.mapper.DepartmentMapper;
import com.jc.crm.model.DepartmentEntity;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.DepartmentService;
import com.jc.crm.service.department.vo.DepartmentMemberVO;
import com.jc.crm.service.department.vo.UserDepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentServiceImpl.java 18-11-22:下午9:43
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    /**
     * 获得用户组（同组、部门）
     *
     * @param uid 用户id
     */
    @Override
    public List<UserEntity> getUsersByDepartment(Integer uid) {
//        departmentMapper.queryUserDepartmentVOByUid(uid);
        return null;
    }

    @Override
    public List<DepartmentMemberVO> getIdsByUser(Integer uid) {
        UserDepartmentVO departmentVO = departmentMapper.getDepartmentDetailByUser(uid);
        if(departmentVO==null) {
            return null;
        }
        return departmentMapper.getDepartmentUserByDepartmentId(departmentVO.getDepartmentId(), departmentVO.getEid());
    }

    @Override
    public boolean isHaveAuth(Integer uid, Integer cuid) {
        UserDepartmentVO departmentVO = departmentMapper.getDepartmentDetailByUser(uid);
        if(departmentVO==null) {
            return false;
        }
        return departmentMapper.isHaveAuth(departmentVO.getWeight(),departmentVO.getDepartmentId(),departmentVO.getEid(),cuid) > 0;
    }

    /**
     * 获取uid部门及其子部门
     *
     * @param uid
     * @param eid
     */
    @Override
    public List<DepartmentEntity> queryDepartment(Integer uid, Integer eid) {
        //todo

        return null;
    }

    /**
     * 查询企业所有部门
     *
     * @param eid
     */
    @Override
    public List<DepartmentEntity> findAllDepartment(Integer eid) {
        return departmentMapper.getDepartments(eid);
    }

    @Override
    public List<DepartmentEntity> findByPid(Integer pid, Integer eid) {
        return departmentMapper.getByPid(pid, eid);
    }

    @Override
    public int updateDepartment(DepartmentUpdateForm departmentUpdateForm) {
        //设置struct
        if (departmentUpdateForm.getPid()!=null) {
            DepartmentEntity departmentEntity =  departmentMapper.getDepartmentEntityById(departmentUpdateForm.getPid());
            String struct = "";
            if (departmentEntity.getStruct()!=null) {
                struct += (departmentEntity.getStruct() + "-" + departmentEntity.getDepartmentId());
            }
            departmentUpdateForm.setStruct(struct);
        }
        return departmentMapper.updateDepartment(departmentUpdateForm);
    }

    @Override
    public int updateUserDepartment() {
        //todo

        return 0;
    }

    @Override
    public int insertUserDepartment(Integer uid, Integer departmentId) {
        //判断是否已经加入部门
        if (departmentMapper.isExistDepartmentUser(uid, departmentId) <= 0) {
            return departmentMapper.insertDepartmentUser(uid, departmentId, null,10);
        }
        return ResultStatus.SUCCESS;
    }

    @Override
    public int insertUserDepartment(Integer uid, String post, int weight) {
        //todo
//        departmentMapper.insertDepartmentUser()
        return 0;
    }

    @Override
    public int deleteUserDepartment(Integer uid, Integer departmentId) {
        return departmentMapper.deleteUserDepartmnet(uid, departmentId);
    }

    @Override
    public int createDepartment(DepartmentCreateForm createForm, Integer eid) {
        if (createForm.getPid()==null) {
            departmentMapper.insert(createForm.toDepartment(null, eid));
        } else {
            DepartmentEntity departmentEntity = departmentMapper.getDepartmentEntityById(createForm.getPid());
            if (departmentEntity!=null) {
                String struct = departmentEntity.getStruct();
                if (struct==null) {
                    struct = createForm.getPid().toString();
                } else {
                    struct = struct + "-" +createForm.getPid();
                }
                return departmentMapper.insert(createForm.toDepartment(struct, eid));
            } else {
                throw new RuntimeException("不存在的父部们");
            }
        }
        return ResultStatus.FAIL;
    }

    @Override
    public int deleteDepartment(Integer departmentId) {
        return departmentMapper.deleteAllByDepartmentId(departmentId);
    }

    /**
     * 获取用户所在的所有小组、部门
     *
     * @param uid
     * @param eid
     */
    @Override
    public List<UserDepartmentVO> getUserDepartment(Integer uid, Integer eid) {
        return departmentMapper.queryUserDepartmentVOByUid(uid, eid);
    }


}
