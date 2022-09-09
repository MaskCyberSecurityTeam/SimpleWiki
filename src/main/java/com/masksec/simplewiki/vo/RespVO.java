package com.masksec.simplewiki.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * http通用响应
 *
 * @author RichardTang
 */
@Data
@Builder
public class RespVO {

    // 响应信息
    private String msg;

    // 状态码
    private Integer code;

    // 携带数据
    private Object data;

    /**
     * 业务成功响应
     */
    public static RespVO success() {
        return RespVO.success("成功");
    }

    /**
     * 业务成功响应
     *
     * @param msg 携带的消息
     */
    public static RespVO success(String msg) {
        return RespVO.success(msg, null);
    }

    /**
     * 业务成功响应
     *
     * @param msg  携带的消息
     * @param data 携带的数据
     */
    public static RespVO success(String msg, Object data) {
        return RespVO.builder().code(HttpStatus.OK.value()).msg(msg).data(data).build();
    }

    /**
     * 业务失败响应
     */
    public static RespVO fail() {
        return RespVO.fail("失败");
    }

    /**
     * 业务失败响应
     *
     * @param msg 携带的消息
     */
    public static RespVO fail(String msg) {
        return RespVO.fail(msg, null);
    }

    /**
     * 业务失败响应
     *
     * @param msg  携带的消息
     * @param data 携带的数据
     */
    public static RespVO fail(String msg, Object data) {
        return RespVO.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(msg).data(data).build();
    }

}
