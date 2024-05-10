package com.bosen.search.vo.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class HotWordsRequestVO implements Serializable {
    private static final long serialVersionUID = 6940045071365469195L;

    private String hotWords;
}
