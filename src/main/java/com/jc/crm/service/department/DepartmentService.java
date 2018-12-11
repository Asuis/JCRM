package com.jc.crm.service.department;

import com.jc.crm.form.department.DepartmentCreateForm;
import com.jc.crm.form.department.DepartmentUpdateForm;
import com.jc.crm.model.DepartmentEntity;
import com.jc.crm.model.UserEntity;
import com.jc.crm.service.department.vo.DepartmentMemberVO;
import com.jc.crm.service.department.vo.UserDepartmentVO;

import java.util.List;

/**
 * @author asuis
 * @version: DepartmentService.java 18-11-22:下午9:14
 */
public interface DepartmentService {
    List<UserEntity> getUsersByDepartment(Integer uid);
    List<DepartmentMemberVO> getIdsByUser(Integer uid);
    boolean isHaveAuth(Integer uid, Integer cuid);
    /**
     * 获取uid部门及其子部门
     * */
    List<DepartmentEntity> queryDepartment(Integer uid,Integer eid);
    /**
     * 查询企业所有部门
     * */
    List<DepartmentEntity> findAllDepartment(Integer eid);
    List<DepartmentEntity> findByPid(Integer pid, Integer eid);
    int updateDepartment(DepartmentUpdateForm departmentUpdateForm);

    int updateUserDepartment();

    int insertUserDepartment(Integer uid, Integer departmentId);

    int insertUserDepartment(Integer uid, String post, int weight);

    int deleteUserDepartment(Integer uid,Integer departmentId);

    int createDepartment(DepartmentCreateForm createForm, Integer eid);

    int deleteDepartment(Integer departmentId);
    /**
     * 获取用户所在的所有小组、部门
     */
    List<UserDepartmentVO> getUserDepartment(Integer uid, Integer eid);

    DepartmentEntity getDepartmentDetail(Integer departmentId);
}
