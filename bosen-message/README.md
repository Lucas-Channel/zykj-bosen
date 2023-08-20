<div align="center">
    <p style="font-size:25px;font-weight: 800;">消息服务</p>
</div>
<div align="center" style="text-align:center;margin-top:30px;margin-bottom:20px">
    <img src="https://img.shields.io/badge/SpringCloudStream -3.2.4-green.svg"/>
    <img src="https://img.shields.io/badge/SpringBoot -2.7.X-green.svg"/>
    <img src="https://img.shields.io/badge/SpringCloud -2021.0.X -green.svg"/>
</div>

## 说明
[SpringCloudStream官网有说明，需要和springCloud以及springBoot版本相对应，不然可能会出现依赖问题，详细版本对应查看地址](https://spring.io/projects/spring-cloud-stream)

## 开发者必看

### SCS架构（Spring Cloud Stream）

#### 为什么选择它
* 所谓换汤不换药，该架构为开发者提供了极大的便利，更加专注于业务实现，而不需要每次因为替换MQ，而重新编写监听消费者。
* SCS架构呢，是Spring Cloud的组件之一，存在即合理，有他存在的意义。
* 编写配置方便，无需代码配置

### 注意事项
* 目前需要注意两个点，bindings下的a-out/in-0,这个是整个框架使用的bindingName，也就是队列名称。bindingName下也有一个destination即mq对应交换机的名称