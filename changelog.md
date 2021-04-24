# step-1.1-solve-SpringInit 
> 实现spring和web项目的整合
- tomcat项目启动时会读取web.xml 文件。
- 我们在web.xml 中配置了listener，那么tomcat 就会通过反射创建这个listener，然后调用里面的模板方法
- 我们在模板方法里面编写了创建IOC容器的代码，所以就实现了web项目和spring 的整合
