package com.masksec.simplewiki.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 对应前端DTree组件的数据结构
 *
 * @author RichardTang
 * @see DTreeVO
 */
@Data
public class DTreeStatusVO {

    private String message = "操作成功";

    private Integer code = HttpStatus.OK.value();

}