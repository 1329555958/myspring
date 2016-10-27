# 虚拟机加载机制
## 如果只是引入类赋值null并不会触发虚拟机加载class
`Hello hello = null`
当Hello不存在于类路径之中时不会报错
## 但是使用了Class.forName 或者 进行了实例化就会报错了
Class.froName 报ClassNotFoundException
new 报NoClassDefFoundError

## 小知识点
- 虚拟机加载时肯定是先加载子类，如果父类还没加载再去加载父类，初始化时肯定是先初始化父类
- 系统类的加载类路径跟自己编写的类路径是不一样的


