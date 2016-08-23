package com.company.service.impl;


import com.company.dao.UserInfoDao;
import com.company.entity.SysUsers;
import com.company.service.UserInfoService;
import com.company.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserDetails loadUserByUsername(String arg0)
            throws UsernameNotFoundException {
        if (StringUtil.isNotEmpty(arg0)) {
            //System.out.println("user name "+ arg0);
            SysUsers users = userInfoDao.getUserByName(arg0.trim());
            if (users != null) {
                return users;
            }
        }
        throw new UsernameNotFoundException(
                "Can't not find user while username is '" + arg0.trim() + "'");
    }

}
