package com.masksec.simplewiki.vo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.masksec.simplewiki.constant.Path;
import lombok.*;

import java.io.File;
import java.util.List;

/**
 * DTree组件中的每个节点
 *
 * @author RichardTang
 * @see DTreeVO
 */
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class DTreeNodeVO {

    // 当前节点id, 文件夹/文件夹的相对路径
    private String id;

    // 文件名/文件名称
    private String title;

    // 是否最后一级节点 (文件夹: false, 文件: true)
    private boolean last;

    // 父节点id, 文件夹/文件夹的相对路径
    private String parentId;

    // 子文件夹/文件
    private List<DTreeNodeVO> children;

    public static DTreeNodeVO getDTreeNode(File file) {
        String pId = file.getAbsolutePath().replace(Path.LIBRARY_DIR.getAbsolutePath(), "");

        return DTreeNodeVO.builder()
                .id(FileUtil.getName(file))
                .title(FileUtil.getName(file))
                .last(file.isFile())
                .parentId(pId)
                .build();
    }
}
