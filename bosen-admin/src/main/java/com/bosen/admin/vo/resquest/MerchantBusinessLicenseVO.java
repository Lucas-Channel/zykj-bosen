package com.bosen.admin.vo.resquest;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/22
 */
@Data
public class MerchantBusinessLicenseVO {
    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 银行收款账号
     */
    @NotBlank
    private String bankAccount;

    /**
     * 开户行
     */
    @NotBlank
    private String bankDeposit;

    /**
     * 公司名称
     */
    @NotBlank
    private String companyName;

    /**
     * 统一社会信用代码
     */
    @NotBlank
    private String unifiedSocialCreditCode;

    /**
     * 主营内容
     */
    @NotBlank
    private String mainBusiness;

    /**
     * 营业执照文件地址
     */
    @NotBlank
    private String businessLicenseUrl;
}
