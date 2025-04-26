package com.bosen.email.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucas
 * @version 2.0.0
 * @date 2024/9/19
 */
@Data
public class EmailFileRequest implements Serializable {

    private static final long serialVersionUID = -6674205179391749301L;

    private String fileName;

    private String filePath;
}
