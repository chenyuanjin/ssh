/*
 * 
 */
package com.company.dao.impl;

import com.company.dao.UserInfoDao;
import com.company.entity.SysUsers;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl extends BaseDao<SysUsers> implements UserInfoDao {

    @Override
    public SysUsers getUserByName(String userName) {
        Object args[] = {
                userName
        };
        String hql = "select * from SysUsers where userName = ?";

        return queryObject(hql, args);
    }
}
