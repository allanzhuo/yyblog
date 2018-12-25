# YYblog简介

一个面向学习的java开源博客系统，减少过度封装，展现技术本质。项目集成PC,WAP,微信小程序等多端，适合学习和二次开发。
## 初衷
很多时候看过的东西只是简单的了解，又或是仅仅做了个Demo，这时候我们所了解的可能远远还达不到生产的级别，不经过实践永远不知道会遇到哪些问题。这是我目前遇到的问题，也是我做这个项目的初衷。并不是为了做一个产品，而是做一个实践，做一个学习的记录。

目前系统分为beta版和1.x版两个分支。beta版为早期开发版本，今后不会再做更新，但是因为使用的技术相对基础，学习意义较大，所以单独开了分支。
1.x版为常规版本，今后相关更新也会基于此版本。最后在此也特别感谢[笔记博客](https://github.com/miyakowork/NoteBlog/)提供的开源前端模板。目前1.x版本前端不会改动，2.x的会全部重构。

微信小程序为最近新增的入口，功能还比较简单，后续会继续完善，在此感谢[juejin第三方](https://github.com/myvin/juejin)提供的开源参考。

项目开发结合实际情况，尽量遵守阿里巴巴开发规范，如有发现相关不合规问题，还请方便提醒下我修改，谢谢。
### 如果本项目对您有帮助的话，请 Star本项目
### 如需关注项目最新动态，也请方便 Star项目，这也是对项目最好的支持

## beta1版
beta1版为早期开发版本，但是基本功能大部分是已经完成的。
#### 功能小结
- 项目基于Springboot 1.5 + mybatis + maven + layui 2.2
- 数据库密码采用MD5双重加密，cookie采用AES对称加密算法
- 登陆拦截采用springmvc的拦截器模式
- 集成QQ登录，腾讯云对象存储等功能

#### 项目运行

下载代码导入Eclipse或者IDEA后：

1、修改application.yml的数据库连接，数据库相关脚本在beta1分支中有上传

2、修改qqconnectconfig.properties的QQ互联的相关连接配置，主要是app_ID，app_KEY，和回调地址redirect_URI

3、修改腾讯云存储的配置，目前在COSClientUtils.java文件中配置即可

## 1.x 版本
1.x 为常规开发版本，目前1.x版本仅作为单体项目开发，重点在于功能的完善，不做任何不必要的拆分。总的来说1.x的项目架构还是有点混乱的，后面会在升级Springboot2.0的时候做全面的重构，预计开始时间大概在年后。

### 项目技术
- 核心框架：Spring Boot 1.5
- 安全框架：Apache Shiro 1.3
- 视图框架：Spring MVC
- 持久层框架：MyBatis
- 定时器：Quartz 2.2
- 数据库连接池：Druid 1.1
- 接口文档：Swagger2
- 前端框架：layui 2.3
- 模板引擎：thymeleaf
- 微信小程序

### 软件需求
- JDK 1.8 +
- MYSQL 5.6 +
- MAVEN 3.0 +

### 开发部署
1. Star本项目。(#^.^#)
2. git下载源码
3. 根据lib下提供的脚本yyblog.sql创建数据库，数据库编码为UTF-8
4. 安装qq互联jar包，地址在lib下面，这是腾讯官方提供的，并不是我封装的。安装方法参考[将jar包安装到maven仓库](https://www.cnblogs.com/laoyeye/p/8933185.html)
5. 修改application.yml文件，更新数据库地址、账号和密码
6. 修改qqconnectconfig.properties文件，更新app_ID、app_KEY和redirect_URI
7. 登陆系统，初始账号：admin 密码为123456
8. 后台设置自己的腾讯云COS配置

### 近期待开发规划
- ~~小程序端开发启动（预览版已上线）~~
- 新增权限管理页面，目前需数据库手动配置
- 新增登录验证码功能
- 新增图片上传自动添加水印功能
- 新增导入导出Excel报表的功能
- 新增评论留言邮件通知功能

### 项目相关技术文档
1. [初尝微信小程序开发与实践](https://www.cnblogs.com/laoyeye/p/9795306.html)
2. [JAVA实用案例之验证码开发](https://www.cnblogs.com/laoyeye/p/6914704.html)
2. [JAVA实用案例之图片水印开发](https://www.cnblogs.com/laoyeye/p/7193309.html)
3. [JAVA实用案例之文件导入导出（POI方式）](https://www.cnblogs.com/laoyeye/p/6938889.html)
4. [JAVA文件导出之jasperreport报表开发](https://www.cnblogs.com/laoyeye/p/7707149.html)
5. [利用Swagger2自动生成对外接口的文档](https://www.cnblogs.com/laoyeye/p/9047504.html)
6. [Quartz实现JAVA定时任务的动态配置](https://www.cnblogs.com/laoyeye/p/9352002.html)
7. [将jar包安装到maven仓库](https://www.cnblogs.com/laoyeye/p/8933185.html)

### 项目演示
- 演示地址：1、前台：www.laoyeye.net 2、后台：http://www.laoyeye.net/management/index
- 账号/密码：test/123456 暂时只拥有部分菜单的查看权限
- 微信小程序演示地址：小程序搜索【小卖铺的老爷爷】查看，或者扫描下方二维码

![image](http://img.laoyeye.net/%E5%B0%8F%E7%A8%8B%E5%BA%8F%E7%A0%81%E6%A0%87%E5%87%86%E7%BB%BF%E7%89%88.png)
### 项目展示
计划任务管理
![image](http://img.laoyeye.net/%E8%AE%A1%E5%88%92%E4%BB%BB%E5%8A%A1.png)
系统日志管理
![image](http://img.laoyeye.net/%E7%B3%BB%E7%BB%9F%E6%97%A5%E5%BF%97.png)
网站设置
![image](http://img.laoyeye.net/%E7%BD%91%E7%AB%99%E8%AE%BE%E7%BD%AE.png)
打赏二维码设置
![image](http://img.laoyeye.net/%E6%89%93%E8%B5%8F.png)
更多界面效果请登录演示地址[www.laoyeye.net](http://www.laoyeye.net/)查看...
