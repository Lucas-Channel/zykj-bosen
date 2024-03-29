<div align="center">
    <p style="font-size:25px;font-weight: 800;">博森</p>
</div>
<div align="center" style="text-align:center;margin-top:30px;margin-bottom:20px">
    <img src="https://img.shields.io/badge/SpringBoot-2.7.9-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/SpringCloud & Alibaba -2021.0.1.0-green.svg"/>
    <img src="https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg"/>
    <img src="https://img.shields.io/badge/Gateway-3.1.3-blue.svg"/>
    <img src="https://img.shields.io/badge/oauth2-5.7.7-critical.svg"/>
    <img src="https://img.shields.io/badge/redis-2.7.4-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/Mysql-8.0.29-ff69b4.svg"/>
    <img src="https://img.shields.io/badge/xxlJob-2.3.0-brightgreen.svg"/>
    <img src="https://img.shields.io/badge/drools-7.73.0-critical.svg"/>
    <img src="https://img.shields.io/badge/elasticsearch-8.5.3-blue.svg"/>
    <img src="https://img.shields.io/badge/camunda-7.19.0-ff69b4.svg"/>
    <img src="https://img.shields.io/badge/SpringCloudStream-3.2.4-brightgreen.svg"/>
</div>

## 前言
博森商城是博森智云科技旗下的一款标准化产品，其几乎涵盖了诸多中小企业甚至大型企业的电商需求。
博森是由目前互联网行业的主流技术框架搭建而成，目的是为了解决企业的零售建站一条龙服务。其主要涵盖了app，小程序，h5，pc以及管理端，形成了多端销售，统一管理。
目前博森致力于实现B2B2C和B2C，在后续的迭代中，将会继续深入企业的痛点，为更多的企业提供一站式解决方案。目前S2B2C已经存在于下一个版本的迭代计划中。
为企业打造标准化、私有化解决方案，实现精准化、透明化、高效化管理。

## 介绍
博森商城主要涵盖了：平台管理，商家管理后台，c端客户，三大标品端。其中平台后台作为整个博森的saas平台，用于统一的管理，只能操作系统的一些基础，以及数据评审。
平台后台致力于统一管理，主要内容包括，系统管理，权限管理，营销管理，商品管理，订单管理，商家管理，内容管理，售后管理，用户管理。商家后台主要涵盖了：商品管理，
订单管理，会员管理，售后管理，营销管理，物流管理，系统管理。c端主要有以下功能：我的（订单，个人中心，购物车，其他），购物，加盟。以上为B2B2C的一个解决方案，在后续的迭代中
，srm将会进入整条产品线中，贯穿整个流程，实现从**采购**到**生产**到**分销**。

## 目录结构

```
zykj-bosen
├─bosen-admin -- 平台后台服务
├─bosen-auth  -- 认证服务
├─bosen-api  -- 接口服务
├─bosen-gateway  -- 网关
├─bosen-common -- 通用服务
├─bosen-marketing -- 营销服务
├─bosen-product -- 商品服务
├─bosen-order -- 订单服务
├─bosen-pay -- 支付服务
├─bosen-search -- 搜索服务
├─bosen-component -- 组件服务
│  ├─bosen-component-redisson  -- redisson
│  └─bosen-component-mq  -- 消息服务
├─bosen-system  -- 系统服务
├─bosen-after-sales  -- 售后服务
├─bosen-seckill  -- 秒杀服务
├─bosen-member  -- 会员服务
├─bosen-camunda  -- 工作流服务
├─bosen-template-adorn  -- 装修服务
└─bosen-other  -- 其他服务
```

[//]: # (/*)

[//]: # (* Copyright &#40;c&#41; 江西博森智云科技有限公司 All rights reserved.)

[//]: # (* 未经允许，不可做商业用途！)

[//]: # (* 版权所有，侵权必究！)

[//]: # (*/)

##环境配置
```aidl
* jdk1.8及以上
* maven 3.6及以上
* idea
* redis
* mysql
* nacos
```
#开发注意事项
##编码规则
* 禁止使用拼音方式进行文件命名和方法名命名，统一使用英文单词编码，如有不明，有道词典。
* domain目录下为数据库表实体映射对象，只存放数据库表中所存在的字段，该对象用于持久化交互。
* vo目录下分有request和response，分别为请求和相应。
* api服务为所有api接口服务，需要引用api，直接引入依赖即可
* 所有接口皆须添加注释。
* 避免使用DO返回。
* 查询sql禁止使用子查询，如有需要，数据库设计时，加入冗余字段
* 所有的查询接口返回对象，不写多余的数据，也不查，前端需要就给
##事务失效相关（事务是一个老生常谈的一个问题，也是开发过程中需要注意的最重要的问题之一）
* 访问权限问题：只有方法是public，才不会失效
* 方法被final修饰，事务会失效
* 直接调用内部方法，比如this
* 没有注入到spring容器
* 线程调用
* 自己捕获异常，但是没有抛出，比如try catch 但是catch只是打印异常或者不做任何操作
* 异常不一致
* 使用自定义异常需要注意，Spring事务，默认情况下只会回滚RuntimeException（运行时异常）和Error（错误），非以上状况，不回滚
##多线程注解问题@Async失效问题（异步处理）
* 注解@Async的方法不是public方法
* 注解@Async的返回值只能为void或者Future
* 注解@Async方法使用static修饰也会失效
* spring无法扫描到异步类，没加注解@Async  或 @EnableAsync注解
* 调用方与被调方不能在同一个类
```aidl
1、Spring 在扫描bean的时候会扫描方法上是否包含@Async注解，动态地生成一个子类（即proxy代理类），当这个有注解的方法被调用的时候，实际上是由代理类来调用的，代理类在调用时增加异步作用。
2、如果这个有注解的方法是被同一个类中的其他方法调用的，那么该方法的调用并没有通过代理类，而是直接通过原来的那个 bean，所以就失效了。
3、所以调用方与被调方不能在同一个类，主要是使用了动态代理，同一个类的时候直接调用，不是通过生成的动态代理类调用。
4、一般将要异步执行的方法单独抽取成一个类。
```
* 在Async 方法上标注@Transactional是没用的，但在Async 方法调用的方法上标注@Transactional 是有效的
###附说明
```aidl
  直接使用 @Async 注解没指定线程池的话，即未设置TaskExecutor时
  默认使用Spring创建ThreadPoolTaskExecutor
  核心线程数：8
  最大线程数：Integer.MAX_VALUE ( 21亿多)
  队列使用LinkedBlockingQueue
  容量是：Integer.MAX_VALUE
  空闲线程保留时间：60s
  线程池拒绝策略：AbortPolicy
```
###C端所有接口，全部从缓存拉取数据，B端更新数据时要注意更新C端的缓存数据

# 使用redis lua脚本注意两个问题
1、分布式下对key增加路由，否则会出现异常
2、内存问题如果qps过大容易导致oom 预加载解决