package com.bosen.admin.controller;

import com.bosen.admin.service.IClientService;
import com.bosen.common.constant.response.ResponseData;
import com.bosen.common.domain.api.ClientDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/2/24
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientDetailController {
    @Resource
    private IClientService clientService;

    @PostMapping("/upsertClient")
    public ResponseData<Void> upsertClient(@RequestBody ClientDetail clientDetail) {
        return clientService.upsertClient(clientDetail);
    }

    /**
     * 此处又一个问题点，如果是从统一授权登录服务进入，异常处理，没处理好
     * @param clientId 客户端id
     * @return 结果
     */
    @GetMapping("/getByClientId")
    public ResponseData<ClientDetail> getByClientId(@RequestParam String clientId) {
        Asserts.notBlank(clientId, "客户端id不能为空");
        ClientDetail clientDetail = clientService.lambdaQuery().eq(ClientDetail::getClientId, clientId).one();
        if (Objects.isNull(clientDetail)) {
            return ResponseData.fail();
        }
        return ResponseData.success(clientDetail);
    }

    /**
     * 删除客户端信息
     * @param ids ids
     * @return 结果
     */
    @PostMapping("/deleteByIds")
    public ResponseData<Void> deleteByIds(@RequestParam("ids") List<Long> ids) {
        Asserts.notNull(ids, "id不能为空");
        return ResponseData.judge(clientService.removeBatchByIds(ids));
    }
}
