ACT_HI_ACTINST	历史节点表
ACT_HI_ATTACHMENT	历史附件表
ACT_HI_COMMENT	历史意见表
ACT_HI_DETAIL	历史详情表，提供历史变量的查询
ACT_HI_IDENTITYLINK	历史流程人员表
ACT_HI_PROCINST	历史流程实例表
ACT_HI_TASKINST	历史任务实例表
ACT_HI_VARINST	历史变量表

静态信息表（ACT_RE_*）
ACT_RE_DEPLOYMENT	部署信息表
ACT_RE_MODEL	流程设计模型部署表
ACT_RE_PROCDEF	流程定义数据表

运行数据（ACT_RU_*）
ACT_RU_DEADLETTER_JOB	无法执行工作表： 如果一个任务执行了很多次，都无法执行，那么这个任务会写到
ACT_RU_EVENT_SUBSCR	运行时事件 throwEvent、catchEvent 时间监听信息表
ACT_RU_EXECUTION	运行时流程执行实例
ACT_RU_IDENTITYLINK	运行时流程人员表，主要存储任务节点与参与者的相关信息
ACT_RU_INTEGRATION	运行时积分表
ACT_RU_JOB	运行时定时任务数据表
ACT_RU_SUSPENDED_JOB	暂停的工作，流程中有一个定时任务，如果把这个任务停止工作了，这个任务会在act_ru_suspended_job中写入数据
ACT_RU_TASK	运行时任务节点表
ACT_RU_TIMER_JOB	运行时定时器作业表
ACT_RU_VARIABLE	运行时流程变量数据表\

Assignee：执行人/代理人
Candidate Users：候选人
Candidate Groups：候选组
Due Date：任务到期时间