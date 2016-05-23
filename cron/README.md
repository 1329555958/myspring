# spring schedule
只需要在spring管理的bean(通过@component，@service等注解的)中的任意方法(无参数无返回值)上使用 @Scheduled(cron = "1 * * * * ?")注解就可以了

# quartz Schedule
[http://www.quartz-scheduler.org/](http://www.quartz-scheduler.org/)
久经历史考验的可靠的定时任务框架;可以通过持久化到数据库中支持集群任务;
job:任务实现
trigger: 触发任务的定时器，可以关联到具体任务，要通过调度器去执行任务
Schedule: 调度器,调度trigger，这里才会执行实际的任务