package com.bosen.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

/**
 * 平台入驻商家-营业信息
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/19
 */
@Data
@TableName("bs_merchant_business_license")
public class MerchantBusinessLicenseDO extends BaseEntityData implements Serializable {

    private static final long serialVersionUID = -7286721434407541328L;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 银行收款账号
     */
    private String bankAccount;

    /**
     * 开户行
     */
    private String bankDeposit;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 统一社会信用代码
     */
    private String unifiedSocialCreditCode;

    /**
     * 主营内容
     */
    private String mainBusiness;

    /**
     * 营业执照文件地址
     */
    private String businessLicenseUrl;
}
