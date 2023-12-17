package com.bosen.admin.vo.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginMenuMetaDetailVO implements Serializable {
    private static final long serialVersionUID = 8912333703129474557L;

    private String title;

    private String i18nTitle;

    private String icon;

    private Integer order;
}
