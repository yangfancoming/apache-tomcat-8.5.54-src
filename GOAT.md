#
    1. 下载源码 apache-tomcat-8.5.54-src.zip   官网： https://tomcat.apache.org/        
        解压、新建 catalina-home 目录，同时将目录中的conf和webapps文件夹复制到catalina-home目录中
        
        
    2. 需要通过Maven组织文件，因此需要在根目录下创建目录中新建pom.xml文件：
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        
            <modelVersion>4.0.0</modelVersion>
            <groupId>org.apache.tomcat</groupId>
            <artifactId>apache-tomcat-8.5.54-src</artifactId>
            <name>apache-tomcat-8.5.54-src</name>
            <version>8.0</version>
        
            <build>
                <finalName>apache-tomcat-8.5.54-src</finalName>
                <sourceDirectory>java</sourceDirectory>
                <testSourceDirectory>test</testSourceDirectory>
                <resources>
                    <resource>
                        <directory>java</directory>
                    </resource>
                </resources>
                <testResources>
                    <testResource>
                        <directory>test</directory>
                    </testResource>
                </testResources>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <version>2.3</version>
                        <configuration>
                            <encoding>UTF-8</encoding>
                            <source>1.8</source>
                            <target>1.8</target>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        
            <dependencies>
                <dependency>
                    <groupId>junit</groupId>
                    <artifactId>junit</artifactId>
                    <version>4.12</version>
                    <scope>test</scope>
                </dependency>
                <dependency>
                    <groupId>org.easymock</groupId>
                    <artifactId>easymock</artifactId>
                    <version>3.4</version>
                </dependency>
                <dependency>
                    <groupId>ant</groupId>
                    <artifactId>ant</artifactId>
                    <version>1.7.0</version>
                </dependency>
                <dependency>
                    <groupId>wsdl4j</groupId>
                    <artifactId>wsdl4j</artifactId>
                    <version>1.6.2</version>
                </dependency>
                <dependency>
                    <groupId>javax.xml</groupId>
                    <artifactId>jaxrpc</artifactId>
                    <version>1.1</version>
                </dependency>
                <dependency>
                    <groupId>org.eclipse.jdt.core.compiler</groupId>
                    <artifactId>ecj</artifactId>
                    <version>4.5.1</version>
                </dependency>
        
            </dependencies>
        </project>

    3. 注释掉 TestCookieFilter 测试类  因为她会报错。。。
    
    4. Tomcat源码启动类 org.apache.catalina.startup.Bootstrap
    
    5. 添加VM options
       
       -Dcatalina.home=catalina-home
       -Dcatalina.base=catalina-home
       -Djava.endorsed.dirs=catalina-home/endorsed
       -Djava.io.tmpdir=catalina-home/temp
       -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
       -Djava.util.logging.config.file=catalina-home/conf/logging.properties
       -Dfile.encoding=UTF-8
    6. 运行项目，访问http://localhost:8080，得到报错结果
        原因是我们直接启动org.apache.catalina.startup.Bootstrap的时候没有加载org.apache.jasper.servlet.JasperInitializer，从而无法编译JSP。
        解决办法是在tomcat的源码org.apache.catalina.startup.ContextConfig 中的configureStart函数中手动将JSP解析器初始化：
        
        源码搜索串： webConfig();
        添加：context.addServletContainerInitializer(new JasperInitializer(), null); // -modify
        
        
# 运行源码 报错： Error:osgi: [tomcat7] Invalid value for Bundle-Version, @VERSION@ does not match [0-9]{1,9}(\.[0-9]{
    解决：
        将apache-tomcat-8.5.23-src\modules\jdbc-pool\resources\MANIFEST.MF中的Bundle-Version: @VERSION@，把Bundle-Version: 8即可