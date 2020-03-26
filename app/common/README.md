# common

    微服务模块需要引用的模块
    包含全部的微服务的通用配置，通用拦截及feign接口和feign交互的pojo
    
### config
    
    全部的通用配置类，要求微服务模块 springboot 扫描的包路径必须包含此目录
    
### feign
    
    本项目全部的 feign 接口及其所需要的 pojo 所在的包，要求微服务模块中的 feign 
    客户端注解扫描的包路径指向此目录
    
### handler

    全部的通用拦截器，要求微服务模块 springboot 扫描的包路径必须包含此目录