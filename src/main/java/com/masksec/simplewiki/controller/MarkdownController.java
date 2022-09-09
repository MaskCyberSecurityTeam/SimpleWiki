package com.masksec.simplewiki.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.masksec.simplewiki.constant.Path;
import com.masksec.simplewiki.vo.EditormdImageUploadVO;
import com.masksec.simplewiki.vo.RespVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 编辑器
 *
 * @author RichardTang
 */
@Controller
@RequestMapping("markdown")
public class MarkdownController {

    @GetMapping
    public ModelAndView indexPage(String fileDir, String fileName, ModelAndView modelAndView) {
        // 要显示的md文件路径
        String path;

        // library目录下首页library\index.md
        if ("index".equals(fileName) && StrUtil.isEmpty(fileDir)) {
            path = Path.LIBRARY_DIR.getAbsolutePath() + File.separator + "index.md";
        }
        // library目录下分类的首页library\XXX\index.md
        else if ("index".equals(fileName)) {
            path = Path.LIBRARY_DIR.getAbsolutePath() + File.separator + fileDir + File.separator + "index.md";
        }
        // library\fileDir\fileName
        else {
            // 过滤目录穿越
            fileDir = fileDir.replaceAll("\\.\\.", "");
            path = Path.LIBRARY_DIR.getAbsolutePath() + File.separator + fileDir;
        }

        if (FileUtil.exist(path)) {
            // 保存数据时需要的条件
            String targetMdFile = path.replaceAll(Path.LIBRARY_DIR.getAbsolutePath() + File.separator, "");
            modelAndView.addObject("targetMdDir", fileDir);
            modelAndView.addObject("targetMdFile", targetMdFile);
            modelAndView.addObject("content", FileUtil.readUtf8String(path));
        } else {
            modelAndView.addObject("content", "该文章不存在");
        }
        modelAndView.setViewName("markdown");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("save")
    public RespVO save(String content, String targetMdFile) {
        if (StrUtil.isEmpty(content)) {
            return RespVO.fail("内容为空!");
        }

        // 过滤目录穿越
        targetMdFile = Path.LIBRARY_DIR.getAbsolutePath() + File.separator + targetMdFile.replaceAll("\\.\\.", "");
        if (!FileUtil.exist(targetMdFile)) {
            return RespVO.fail("文件不存在!");
        }

        try {
            String fileContent = Base64.decodeStr(content);
            FileUtil.writeString(fileContent, targetMdFile, StandardCharsets.UTF_8);
            return RespVO.fail("保存成功!");
        } catch (Exception e) {
            return RespVO.fail("保存失败!");
        }
    }

    @ResponseBody
    @PostMapping("upload")
    public EditormdImageUploadVO upload(@RequestParam(value = "editormd-image-file") MultipartFile file,
                                        String targetMdDir) {
        String fileName = UUID.randomUUID() + ".jpg";

        // 防目录穿越
        targetMdDir = targetMdDir.replaceAll("\\.\\.", "").replaceAll("\\\\", "/");
        targetMdDir = StrUtil.subBefore(targetMdDir, "/", true);
        if (StrUtil.isEmpty(targetMdDir) || "/".equals(targetMdDir)) {
            targetMdDir = File.separator + "Root";
        }

        // 存储至盘中的路径
        String saveDir = Path.RESOURCE_DIR + targetMdDir + File.separator;

        // 目录不存在时创建，不然transferTo会报错。
        if (!FileUtil.exist(saveDir)) {
            FileUtil.mkdir(saveDir);
        }

        try {
            file.transferTo(new File(saveDir + fileName));
        } catch (IOException e) {
            return EditormdImageUploadVO.fail();
        }

        return EditormdImageUploadVO.success("resource" + targetMdDir + File.separator + fileName);
    }

}