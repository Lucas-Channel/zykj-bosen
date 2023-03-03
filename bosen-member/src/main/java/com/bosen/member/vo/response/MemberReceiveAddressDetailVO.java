package com.bosen.member.vo.response;

import com.bosen.common.domain.BaseEntityData;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberReceiveAddressDetailVO extends BaseEntityData implements Serializable {
    private static final long serialVersionUID = 3687744207089212200L;

    private Long memberId;

    /**
     * 收货人名称
     */
    private String name;

    private String phoneNumber;

    /**
     * 是否为默认
     */
    private Integer defaultStatus;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 省份/直辖市
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区
     */
    private String region;

    /**
     * 详细地址(街道)
     */
    private String detailAddress;

}