## 开发日记
### 项目目录结构和pom表
项目是通过一个管理项目中嵌套一个module来完成后端的模块化设计的。
在parent的pom表中新增了如下东西:

配置UTF-8
```xml
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncode>UTF-8</project.build.sourceEncode>
        <project.reporing.outputEncode>UTF-8</project.reporing.outputEncode>
    </properties>
```

配置模块和spring boot的项目插件
```xml
<project>
    <packaging>pom</packaging>

    <modules>
        <module>blog-api</module>
    </modules>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.3</version>
    </parent>
</project>
```

配置依赖的话可能会有不成功的情况出现，会提示dependencies:unknown的字样
通过新增version字段成功的，依赖包的查询网站如下：
https://mvnrepository.com/

### application配置

配置的端口指的是前端需要访问的端口，这个8888是前端访问的localhost端口
```lombok.config
server.port=8888
spring.application.name=childy_blog
```

下面是mysql和mybatis的配置，相关配置可以查询官方文档。
其中mybatis的作用主要是数据库的辅助类。
```config
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#mybatis-plus
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

### Springboot配置
此处需要查询一下，所谓的跨域配置指的是什么？浏览器访问的到底是8080还是8888？

```java
public class WebMVCConfig implements WebMvcConfigurer {
    //跨域配置？前后端的域名和端口是不一样的.
    //浏览器使用的是8080端口，需要将这个端口配置可以进行跨域的配置？
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
```

这个也不太懂，MapperScan和分页插件都是属于mybatis的。
需要查询一下官方文档说明
```java
@Configuration
//没有mapper好像不太能运行啊？
//@MapperScan("com.childy.blog.mapper")
public class MyBatisConfig {
    //分页插件，这是什么东西？
    @Bean
    public MybatisPlusInterceptor getInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
```
### 代码上传问题
上传代码遇到了Failed to connect to github.com port 443 after
问题，这次是通过git命令设置代理和取消代理实现的。
不知道下次行不行
```bash
git config --global https.proxy
git config --global --unset https.proxy
```
设置切换git的http 和 ssh的命令是
```bash
// 1. 查看当前remote
git remote -v
// 2. 切换到http：
git remote set-url origin https://github.com/username/repository.git
// 3. 切换到ssh：
git remote set-url origin git@github.com:username/repository.git
```


### 数据库表结构
#### 问题
* 使用md5算法必须要用到加密盐？

dao包名，用来做数据库管理类。
pojo中的实体类需要在mapper包中创建一个Mapper类，对应的，需要在Mybatis中的MapperScan中修改对应的mapper位置，否则无法扫描

扫描方式其实是名字相关的，需要数据库中的表和类名保持一致。


### Controller结构
下面的RequestMapping中的articles，是浏览器url中的地址
```java
//json交互
@RestController
@RequestMapping("articles")
public class ArticleController {
}
```


