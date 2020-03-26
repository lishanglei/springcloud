#springCloud学习
    主要是学习springCloud的相关组件以及框架搭建

##core模块
    整个项目的一些公共用到的但却与模块,
    框架之间没有关联的一些内容,比如工具类,枚举值等等

##app模块
    项目的框架模块及业务模块

###基础知识的记录:
modelVersion标签

    <!--    maven 模型的版本号-->
    <modelVersion>4.0.0</modelVersion>

packageing标签

    当该模块下不存在其他模块时,使用jar
    <packaging>jar</packaging>

    当该模块下存在其他模块时,使用pom
    <packaging>pom</packaging>
    
    
dependencies标签 和 dependencyManagement标签

    dependencies管理的是依赖关系,即使子模块不写该依赖依然会继承父模块中的依赖
    
    dependencyManagement管理的是版本,如果子模块不写该依赖则不会继承父模块中的依赖,
    只有子模块中写了该依赖却没有写依赖的版本,才会向上查找父模块中的依赖版本进行依赖,
    version和scope与父模块相同
