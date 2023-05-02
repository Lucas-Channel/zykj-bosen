##介绍：
- xxl_job_group：执行器信息表，维护任务执行器信息；
- xxl_job_info：调度扩展信息表： 用于保存XXL-JOB调度任务的扩展信息，如任务分组、任务名、机器地址、执行器、执行入参和报警邮件等等；
- xxl_job_lock：任务调度锁表；
- xxl_job_log：调度日志表： 用于保存XXL-JOB任务调度的历史信息，如调度结果、执行结果、调度入参、调度机器和执行器等等；
- xxl_job_log_report：调度日志报表：用户存储XXL-JOB任务调度日志的报表，调度中心报表功能页面会用到；
- xxl_job_logglue：任务GLUE日志：用于保存GLUE更新历史，用于支持GLUE的版本回溯功能；
- xxl_job_registry：执行器注册表，维护在线的执行器和调度中心机器地址信息；
- xxl_job_user：系统用户表；

##应用介绍：
服务应用xxl-job，需要引入core包，然后在对应的配置文件中添加相关配置
xxl:
    job:
        admin:
            addresses: http://127.0.0.1:8080/xxl-job-admin
        accessToken: default_token
        executor:
            ip:
            appname: ${spring.application.name}
            address:
            port: 18111
            logpath: /Applications/work/wms/logs/jobhandler
            logretentiondays: 30
对应服务使用注解式开发调度任务，案例可以在system服务找到