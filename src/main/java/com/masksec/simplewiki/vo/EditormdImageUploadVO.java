package com.masksec.simplewiki.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Editor.md组件文件上传后的相应数据格式
 *
 * @author RichardTang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditormdImageUploadVO {

    // 0 表示上传失败，1 表示上传成功
    public Integer success;

    // 提示的信息，上传成功或上传失败及错误信息等。
    private String message;

    // 图片地址，上传成功时才返回。
    private String url;

    public static EditormdImageUploadVO success(String url) {
        return resp(1, "上传成功", url);
    }

    public static EditormdImageUploadVO fail() {
        return resp(0, "上传失败", "");
    }

    public static EditormdImageUploadVO resp(Integer success, String message, String url) {
        return new EditormdImageUploadVO(success, message, url);
    }
}