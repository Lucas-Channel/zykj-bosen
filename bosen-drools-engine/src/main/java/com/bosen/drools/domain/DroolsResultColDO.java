package com.bosen.drools.domain;


import java.io.Serializable;

/**
 * 规则结果列表(BsDroolsResultCol)表实体类
 *
 * @author Lucas
 * @since 2023-05-15 14:10:48
 */
@Data
@TableName(bs_drools_result_col)
public class DroolsResultColDO extends BaseEntityData implements Serializable {
    //主键
    private String id;
    //规则结果列编码
    private String droolsResultColCode;
    //规则结果列名称
    private String droolsResultColName;
    //规则结果列代码
    private String droolsResultColScript;
    //描述
    private String droolsResultColDesc;
    //启用状态：1，启用，0禁用
    private Integer status;
    
    private Date createTime;
    
    private Date updateTime;
    
    private String updaterUser;
    
    private String creatorUser;
    
    private Integer delFlag;
}

