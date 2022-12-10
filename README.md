# SimpleWiki

SimpleWiki - 用于展示Markdown文件的内部小型文档库程序

## 文件说明

* SimpleWiki.zip
  * library 文档文件夹，将需要展示的Markdown文件放置该目录。
  * resource 资源文件夹，Markdown文件中引用的图片放置该目录。
  * SimpleWiki.db 数据库(里边就一张User表目前 :P)
  * SimpleWiki.jar 主程序

## 运行

运行环境需要使用`JRE11`

```bash
nohup java -jar SimpleWiki.jar --spring.profiles.active=prod --server.port=8081 --simplewiki.title=SimpleWiki &
```

默认账号: `admin`
默认密码: `simplewiki`

![image](https://user-images.githubusercontent.com/30547741/189382967-355e2fe3-1267-4d72-b43a-b9813ed5e96c.png)

![image](https://user-images.githubusercontent.com/30547741/189383591-b6feda11-b128-4ef0-a99a-583d850b205a.png)

![image](https://user-images.githubusercontent.com/30547741/189383665-98c6dd8a-b599-4e2c-ba1d-24ca698f011d.png)

# 关于图片

**Markdown中的图片存放至`resource`目录下，不然在web里访问不到。**

在线编写时上传和粘贴图片会自动上传至`resource`目录下

Markdown应用图片时的写法: `![](resource/xxx/xxx.png)`

# BugFix

* 在搜索出的文件夹基础上创建文件，文件创建的路径存在问题。
* 图片上传时应网络问题缓慢，缺少加载动画。

