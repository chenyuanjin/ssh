package com.company.dao;

import com.company.entity.SysUsers;

public interface UserInfoDao {

    SysUsers getUserByName(String userName);
}
