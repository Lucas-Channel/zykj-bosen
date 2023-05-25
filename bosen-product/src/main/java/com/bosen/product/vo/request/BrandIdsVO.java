package com.bosen.product.vo.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2023/5/25
 */
@Data
public class BrandIdsVO implements Serializable {
    private static final long serialVersionUID = -4385758776648674529L;

    private List<String> ids;
}
