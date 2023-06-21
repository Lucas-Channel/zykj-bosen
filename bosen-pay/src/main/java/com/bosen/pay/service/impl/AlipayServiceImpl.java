package com.bosen.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.bosen.common.constant.response.ResponseCode;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.exception.BusinessException;
import com.bosen.pay.api.vo.request.alipay.AlipayRequestBase;
import com.bosen.pay.api.vo.request.alipay.AlipayScanCodePayRequestVO;
import com.bosen.pay.component.PayParamsConfig;
import com.bosen.pay.service.IAlipayService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 支付宝支付-接口逻辑
 * @author Lucas
 * @version 2.0.0
 * @date 2023/6/19
 */
@Service
public class AlipayServiceImpl implements IAlipayService {

    @Resource
    private PayParamsConfig payParamsConfig;

    @Override
    public ResponseData<String> scanCodeToPay(AlipayScanCodePayRequestVO payRequestVO) {
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
    public ResponseData<String> scanCodeFaceToFaceToPay(AlipayScanCodePayRequestVO payRequestVO) {
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
            request.setReturnUrl(payRequestVO.getReturn_url());
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
    public ResponseData<String> appToPay(AlipayScanCodePayRequestVO payRequestVO) {
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
            request.setReturnUrl(payRequestVO.getReturn_url());
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
    public ResponseData<String> mobileWebToPay(AlipayScanCodePayRequestVO payRequestVO) {
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
