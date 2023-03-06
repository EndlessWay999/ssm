package com.endlessway.service.impl;

import com.endlessway.dao.UserDao;
import com.endlessway.pojo.SysUser;
import com.endlessway.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


@Service("userServiceImpl")
@Scope("prototype")
@Data
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init(){
        System.out.println("init方法");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("destroy方法");
    }

    @Override
    public List<SysUser> selectUser() {
        return userDao.selectUser();
    }
}
