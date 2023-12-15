package com.bosen.admin.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LoginMenuDetailVO implements Serializable {
    private static final long serialVersionUID = -7177241693806061177L;

    private String name;

    private String path;

    private String component;

    private LoginMenuMetaDetailVO meta;

    private List<LoginMenuDetailVO> children;
}
