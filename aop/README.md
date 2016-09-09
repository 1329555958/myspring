# aop
aop 可以被拦截到的bean，在spring Ioc容器中已经是代理类了
aop 的包引入类路径的时候,会有一个BeanPostProcess在容器中AnnotationAwareAspectJAutoProxyCreator
这里会在创建bean的时候就会判断是否需要代理，如果需要就进行代理创建
 Creating implicit proxy for bean 'cat' with 0 common interceptors and 2 specific interceptors
 
自己也可以实现一个BeanPostProcess来进行拦截bean的生命周期
还可以实现一个BeanFactoryPostProcess来添加整个容器的初始化之后的动作



