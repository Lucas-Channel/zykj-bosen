package com.bosen.marketing.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 平台活动商家报名表(BsPlatformActivityMerchantApply)表实体类
 *
 * @author Lucas
 * @since 2023-09-12 13:52:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("bs_platform_activity_merchant_apply")
public class BsPlatformActivityMerchantApply extends BaseEntityData implements Serializable {

        private static final long serialVersionUID = -331044690038217001L;


        /**
    * 活动id
    **/
        private String activityId;

        /**
    * 报名时间
    **/
        private LocalDateTime applyDateTime;

        /**
    * 商家id
    **/
        private String merchantId;

        /**
    * 商家角色id
    **/
        private String merchantRoleId;

        /**
    * 商家名称
    **/
        private String merchantName;

        /**
    * 活动规则
    **/
        private Object activityRule;

        /**
    * 审核状态：0，待审核，1，审核通过，2、审核不通过
    **/
        private Integer status;
}

