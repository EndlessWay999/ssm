package com.endlessway.pojo;

import lombok.Data;

@Data
public class SysUser {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String valid;
    private String deptId;
    private String email;
    private String mobile;
}
