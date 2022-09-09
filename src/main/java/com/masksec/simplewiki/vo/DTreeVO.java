package com.masksec.simplewiki.vo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.masksec.simplewiki.constant.Path;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 对应前端DTree组件的数据结构
 * <br>
 * <code>
 * {
 * "status": {
 * "code": 200,
 * "message": "操作成功"
 * },
 * "data": [{
 * "id": "ICEFlow",
 * "title": "ICEFlow",
 * "last": false,
 * "parentId": "0",
 * "children": null
 * }, {
 * "id": "Zzzcms",
 * "title": "Zzzcms",
 * "last": false,
 * "parentId": "0",
 * "children": null
 * }]
 * }
 * </code>
 *
 * @author RichardTang
 */
@Data
public class DTreeVO {

    // 状态，基本上是固定的。
    private DTreeStatusVO status;

    // 展示的数据
    private List<DTreeNodeVO> data;

    private DTreeVO(List<DTreeNodeVO> data) {
        this.data = data;
        this.status = new DTreeStatusVO();
    }

    /**
     * 获取目录树
     *
     * @param libraryChildDir 需要展示的library目录下的哪个目录
     * @return 符合dtree组件数据结构的对象
     */
    public static DTreeVO getCatalog(String libraryChildDir) {
        File rootPath = Path.LIBRARY_DIR;
        List<DTreeNodeVO> items = new ArrayList<>();

        // fileDir不为空，则代表需要展示的数据是 library\${fileDir} 这个路径下的文件。
        if (StrUtil.isNotEmpty(libraryChildDir)) {
            rootPath = new File(rootPath.getAbsolutePath() + File.separator + libraryChildDir);
        }

        // 文件存在情况下进行遍历
        if (FileUtil.exist(rootPath)) {
            for (File file : rootPath.listFiles()) {
                if (file.isDirectory() || file.getName().contains(".md")) {
                    items.add(DTreeNodeVO.getDTreeNode(file));
                }
            }
        }

        return new DTreeVO(items);
    }

    /**
     * 搜索目录数树的节点
     *
     * @param keyword 需要搜索的关键字
     * @return 节点含有关键字的目录数集合数据
     */
    public static DTreeVO searchCatalog(String keyword) {
        // 未输入关键字的情况下，则直接获取library目录数
        if (StrUtil.isEmpty(keyword)) {
            return getCatalog(null);
        }

        List<DTreeNodeVO> items = new ArrayList<>();
        for (File file : Path.LIBRARY_DIR.listFiles()) {
            if (file.isFile() && file.getName().contains(keyword)) {
                items.add(DTreeNodeVO.getDTreeNode(file));
            }
        }
        return new DTreeVO(items);
    }
}