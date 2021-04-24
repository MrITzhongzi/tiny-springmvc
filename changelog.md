# step-1.1-solve-SpringInit 
> 实现spring和web项目的整合
- tomcat项目启动时会读取web.xml 文件。
- 我们在web.xml 中配置了listener，那么tomcat 就会通过反射创建这个listener，然后调用里面的模板方法
- 我们在模板方法里面编写了创建IOC容器的代码，所以就实现了web项目和spring 的整合

# step-1.2-solve-SpringMVCInit

> 实现springmvc的初始化

- 编写一个servlet，重写init 方法，将这个servlet 配置在web.xml 中。
- 那么web项目启动的时候，就会读取web.xml 然后创建出这个servlet并调用初始化方法 进行servlet的初始化。
- init的重要逻辑：我们先从ServletContext获取通过ServletContextListener 的初始化方法注入的父容器。然后再根据 **mvc配置文件 + 父容器** 创建出来的容器就是 MVC容器。

