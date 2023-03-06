package com.endlessway.dao;

import com.endlessway.pojo.SysUser;

import java.util.List;

public interface UserDao {
   List<SysUser> selectUser();
}
