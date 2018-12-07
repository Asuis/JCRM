package com.jc.crm.service.user.impl;

import com.jc.crm.service.user.UserManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author asuis
 * @version: UserManagerServiceImpl.java 18-12-7:下午6:47
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {
    @Override
    public int registerFromList() {
        return 0;
    }

    @Override
    public int deleteFromList(List<Integer> userList) {
        return 0;
    }

    @Override
    public String exportUserList(int eid) {
        return null;
    }
}
