package com.jc.crm.service.user;

import java.util.List;

/**
 * 用户管理接口
 * */
public interface UserManagerService {
    int registerFromList();
    int deleteFromList(List<Integer> userList);
    String exportUserList(int eid);
}
