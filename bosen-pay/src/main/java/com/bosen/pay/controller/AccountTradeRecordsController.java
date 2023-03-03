package com.bosen.pay.controller;

import com.bosen.pay.service.IAccountTradeRecordsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/3/1
 */
@RestController
@RequestMapping("/account/trade/records")
public class AccountTradeRecordsController {

    @Resource
    private IAccountTradeRecordsService accountTradeRecordsService;


}
