package com.bosen.search.vo.request;

import com.bosen.common.domain.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class HomePageQueryVO extends PageVO implements Serializable {
    private static final long serialVersionUID = 4886487048484425368L;


}
