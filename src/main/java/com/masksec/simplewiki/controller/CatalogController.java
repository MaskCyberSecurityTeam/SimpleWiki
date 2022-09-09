package com.masksec.simplewiki.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.masksec.simplewiki.constant.Path;
import com.masksec.simplewiki.vo.DTreeVO;
import com.masksec.simplewiki.vo.RespVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * 目录树
 *
 * @author RichardTang
 */
@Controller
@RequestMapping("catalog")
public class CatalogController {

    @GetMapping
    @ResponseBody
    public DTreeVO catalog(String fileDir) {
        // 过滤，防止目录穿越。
        if (StrUtil.isNotEmpty(fileDir)) {
            fileDir = fileDir.replaceAll("\\.", "");
        }
        return DTreeVO.getCatalog(fileDir);
    }

    @ResponseBody
    @GetMapping("search")
    public DTreeVO search(String keyword) {
        return DTreeVO.searchCatalog(keyword);
    }

    @ResponseBody
    @PostMapping("add")
    public RespVO add(String nodeName, String nodeType, String nodeDir) {
        // 防目录穿越
        nodeName = nodeName.replaceAll("\\.\\.", "");
        nodeDir = Path.LIBRARY_DIR.getAbsolutePath() + File.separator + nodeDir.replaceAll("\\.\\.", "");

        if (!FileUtil.isDirectory(nodeDir)) {
            return RespVO.fail("创建失败!");
        }

        if ("file".equals(nodeType)) {
            String path = nodeDir + File.separator + nodeName + ".md";
            if (FileUtil.exist(path)) {
                return RespVO.fail("该文件已经存在!");
            }
            FileUtil.touch(path);
        } else {
            String path = nodeDir + File.separator + nodeName;
            if (FileUtil.exist(path)) {
                return RespVO.fail("该文件夹已经存在!");
            }
            FileUtil.mkdir(path);
        }

        return RespVO.success("创建成功!");
    }

    @ResponseBody
    @GetMapping("del")
    public RespVO del(String targetFilePath) {
        // 防目录穿越
        targetFilePath = Path.LIBRARY_DIR.getAbsolutePath() + File.separator + targetFilePath.replaceAll("\\.\\.", "");
        return FileUtil.del(targetFilePath) ? RespVO.success() : RespVO.fail();
    }

    @ResponseBody
    @PostMapping("edit")
    public RespVO edit(String newName, String targetFilePath) {
        // 防目录穿越
        newName = newName.replaceAll("\\.\\.", "");
        targetFilePath = Path.LIBRARY_DIR.getAbsolutePath() + File.separator + targetFilePath.replaceAll("\\.\\.", "");

        if (!FileUtil.exist(targetFilePath)) {
            return RespVO.fail("文件不存在!");
        }

        FileUtil.rename(new File(targetFilePath), newName, true, true);
        return RespVO.success("修改成功!");
    }
}