package com.bosen.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.common.util.MapDataUtil;
import com.bosen.pay.api.vo.request.alipay.AlipayRefundRequestVO;
import com.bosen.pay.api.vo.request.alipay.AlipayRequestBase;
import com.bosen.pay.api.vo.request.alipay.AlipayRequestVO;
import com.bosen.pay.component.PayParamsConfig;
import com.bosen.pay.service.IAlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * 支付宝支付-接口逻辑
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/19
 */
@Slf4j
@Service
public class AlipayServiceImpl implements IAlipayService {

    @Resource
    private PayParamsConfig payParamsConfig;

    @Override
    public ResponseData<String> scanCodeToPay(AlipayRequestVO payRequestVO) {
        checkUrl();
        AlipayConfig alipayConfig = this.alipayConfigSet(payRequestVO);
        AlipayClient alipayClient;
        try {
            alipayClient = new DefaultAlipayClient(alipayConfig);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            AlipayTradePagePayModel model = new AlipayTradePagePayModel();
            model.setOutTradeNo(payRequestVO.getOut_trade_no());
            model.setTotalAmount(payRequestVO.getTotal_amount().toString());
            model.setSubject(payRequestVO.getSubject());
            model.setBody(payRequestVO.getBody());
            request.setBizModel(model);
            request.setReturnUrl(payRequestVO.getReturn_url());
            request.setNotifyUrl(payRequestVO.getNotify_url());
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                return ResponseData.success(response.getBody());
            } else {
                return ResponseData.fail(ResponseCode.ALIPAY_REQUEST_ERROR);
            }
        } catch (AlipayApiException e) {
            throw new BusinessException(ResponseCode.ALIPAY_REQUEST_ERROR);
        }
    }

    @Override
    public ResponseData<String> scanCodeFaceToFaceToPay(AlipayRequestVO payRequestVO) {
        checkUrl();
        AlipayConfig alipayConfig = this.alipayConfigSet(payRequestVO);
        AlipayClient alipayClient;
        try {
            alipayClient = new DefaultAlipayClient(alipayConfig);
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
            model.setOutTradeNo(payRequestVO.getOut_trade_no());
            model.setTotalAmount(payRequestVO.getTotal_amount().toString());
            model.setSubject(payRequestVO.getSubject());
            model.setBody(payRequestVO.getBody());
            request.setBizModel(model);
            request.setNotifyUrl(payRequestVO.getNotify_url());
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return ResponseData.success(response.getQrCode());
            } else {
                return ResponseData.fail(ResponseCode.ALIPAY_REQUEST_ERROR);
            }
        } catch (AlipayApiException e) {
            throw new BusinessException(ResponseCode.ALIPAY_REQUEST_ERROR);
        }
    }

    @Override
    public ResponseData<String> appToPay(AlipayRequestVO payRequestVO) {
        checkUrl();
        AlipayConfig alipayConfig = this.alipayConfigSet(payRequestVO);
        AlipayClient alipayClient;
        try {
            alipayClient = new DefaultAlipayClient(alipayConfig);
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setOutTradeNo(payRequestVO.getOut_trade_no());
            model.setTotalAmount(payRequestVO.getTotal_amount().toString());
            model.setSubject(payRequestVO.getSubject());
            model.setBody(payRequestVO.getBody());
            request.setBizModel(model);
            request.setNotifyUrl(payRequestVO.getNotify_url());
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                return ResponseData.success(response.getBody());
            } else {
                return ResponseData.fail(ResponseCode.ALIPAY_REQUEST_ERROR);
            }
        } catch (AlipayApiException e) {
            throw new BusinessException(ResponseCode.ALIPAY_REQUEST_ERROR);
        }
    }

    @Override
    public ResponseData<String> mobileWebToPay(AlipayRequestVO payRequestVO) {
        checkUrl();
        AlipayConfig alipayConfig = this.alipayConfigSet(payRequestVO);
        AlipayClient alipayClient;
        try {
            alipayClient = new DefaultAlipayClient(alipayConfig);
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setOutTradeNo(payRequestVO.getOut_trade_no());
            model.setTotalAmount(payRequestVO.getTotal_amount().toString());
            model.setSubject(payRequestVO.getSubject());
            model.setBody(payRequestVO.getBody());
            request.setBizModel(model);
            request.setReturnUrl(payRequestVO.getReturn_url());
            request.setNotifyUrl(payRequestVO.getNotify_url());
            AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                return ResponseData.success(response.getBody());
            } else {
                return ResponseData.fail(ResponseCode.ALIPAY_REQUEST_ERROR);
            }
        } catch (AlipayApiException e) {
            throw new BusinessException(ResponseCode.ALIPAY_REQUEST_ERROR);
        }
    }

    @Override
    public ResponseData<Void> refund(AlipayRefundRequestVO refundRequestVO) {
        checkUrl();
        AlipayConfig alipayConfig = this.alipayConfigSet(refundRequestVO);
        AlipayClient alipayClient;
        try {
            alipayClient = new DefaultAlipayClient(alipayConfig);
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(refundRequestVO.getOut_trade_no());
            model.setTradeNo(refundRequestVO.getTrade_no());
            model.setRefundAmount(refundRequestVO.getRefund_amount().toString());
            request.setBizModel(model);
            request.setReturnUrl(refundRequestVO.getReturn_url());
            request.setNotifyUrl(refundRequestVO.getNotify_url());
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return Objects.equals(response.getFundChange(), "Y") ? ResponseData.success() : ResponseData.fail();
            } else {
                return ResponseData.fail(ResponseCode.ALIPAY_REQUEST_ERROR);
            }
        } catch (AlipayApiException e) {
            throw new BusinessException(ResponseCode.ALIPAY_REQUEST_ERROR);
        }
    }

    @Override
    public String payCallBack(Map<String, Object> body, HttpServletRequest request) {
        Map<String, String> parameterMap = MapDataUtil.getParameterMap(request);
        log.info("回调通知参数：{}", JSON.toJSONString(parameterMap));
        String backResult = "FAILURE";
        try {
            boolean verify_result = AlipaySignature.rsaCheckV1(parameterMap, "公钥", "UTF-8",
                    "RSA2");
            if (verify_result) {
                backResult = "SUCCESS";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return backResult;
    }

    private void checkUrl() {
        if (!StringUtils.hasLength(payParamsConfig.getAlipayBaseUrl()) || null == payParamsConfig.getAlipayBaseUrl()) {
            throw new BusinessException(ResponseCode.THIRD_URL_NOT_EXIT_ERROR);
        }
    }

    public AlipayConfig alipayConfigSet(AlipayRequestBase payRequestVO) {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(payParamsConfig.getAlipayBaseUrl());
        alipayConfig.setAppId(payRequestVO.getApp_id());
        alipayConfig.setPrivateKey(payRequestVO.getPrivate_key());
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(payRequestVO.getPublic_key());
        alipayConfig.setCharset(payRequestVO.getCharset());
        alipayConfig.setSignType(payRequestVO.getSign_type());
        return alipayConfig;
    }
}
