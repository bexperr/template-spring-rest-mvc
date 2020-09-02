# PLANTILLA PROYECTO WEB JAVA SPRING MVC

Proyecto realizado en **Eclipse** usando **Java 8** ,**Spring 5.2.0.RELEASE**,**Maven** y **MyBatis**.
Este un proyecto que te puede servir para configurar tus propios proyectos usando estas tecnologías y las configuraciones aquí mostradas. 

### Detalles técnicos

 - Java 1.8
 - Eclipse IDE for Enterprise Java Developers 2020-03 (4.15.0)
 - Spring 5.2.0.RELEASE
 - WebLogic 12.2.1.2

## Pasos para crear el proyecto
### Creacion del proyecto

 1. File / New / DynamicWebProject   (Se abre una ventana)
 2. Project name: "El nombre de tu proyecto"
 3. Tarjet runtime : Oracle WebLogic (El que tu tengas instalado)
 4. Dynamic web module version: 3.1  / Next > / Next >
 5. Content directory: webapp  (Puedes dejar la que esta por default)
 6. Checkbox marcado de "Generate web.xml deployment descriptor
 7. Finish

### Maven

 1. Click derecho al proyecto y seleccionar.
 2. Configure / Convert to Maven Project
 3. Packaging: WAR
 4. Finish


## Estructura de Paquetes

Ejemplo de como se estructuran los paquetes para la correcta configuración de los frameworks integrados.

|          INFO                |PACKAGE                         |
|-------------------------------|-----------------------------|
|Paquete principal de clases Java | `com.bexperr.app`|
|Paquete donde se crean todos los controladores Spring   | `com.bexperr.app.controller`|
|Interfaces Java con Acceso a Datos          |`com.bexperr.app.dao`|
|Archivos .xml que tienen la configuración de las consultas con **MyBatis**   |`com.bexperr.app.dao.mapper` |
|Clases Java con la notación @Service     | `com.bexperr.app.service`|


## Configuraciones Spring

*Para ahorrarte tiempo, puedes copiar las configuraciones de los siguientes archivos directamente en el **PATH** mostrado dentro de este proyecto.*


|          NAME                |PATH                         |
|-------------------------------|-----------------------------|
|web.xml | [`/webapp/WEB-INF/web.xml`](https://github.com/bexperr/template-spring-rest-mvc/blob/master/webapp/WEB-INF/web.xml)|

	<!-- La definición de Root Spring Container compartida por todos los servlets y filtros -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>

	<!-- Crea el contenedor Spring compartido por todos los servlets y filtros -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<servlet>
		<description></description>
		<display-name>servlet</display-name>
		<servlet-name>servlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>


|          NAME                |PATH                         |
|-------------------------------|-----------------------------|
|root-context.xml | [`/webapp/WEB-INF/spring/root-context.xml`](https://github.com/bexperr/template-spring-rest-mvc/blob/master/webapp/WEB-INF/spring/root-context.xml)|


	<context:annotation-config></context:annotation-config>
	<context:component-scan
		base-package="com.bexperr.app.service" />
		
<!-- Enables the Spring MVC @Controller programming model -->
	<mvc:annotation-driven></mvc:annotation-driven>

	<!-- configuracion para carga de archivos -->
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="10000000000" />
	</bean>


	<!-- configuracion conexion base de datos -->
	<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean"
		autowire-candidate="default">
		<property name="jndiName" value="/jdbc/bexperr" />
		<property name="lookupOnStartup" value="false" />
		<property name="cache" value="true" />
		<property name="proxyInterface" value="javax.sql.DataSource" />
	</bean>


	<!-- configuracion transacciones -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource" />
	</bean>

	<!-- configuracion de mappers -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"></property>
		<property name="typeAliasesPackage" value="com.bexperr.app.vo"></property>
		<property name="mapperLocations" value="classpath*:com/bexperr/app/dao/mapper/*.xml"></property>		
	</bean>


	<!-- configuracion de sqlSession -->
	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg index="0" ref="sqlSessionFactory"></constructor-arg>
	</bean>

	<!-- configuracion conexion de los daos con los mappers xml -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="com.bexperr.app.dao" />
	</bean>

|          NAME                |PATH                         |
|-------------------------------|-----------------------------|
|servlet-context.xml | [`/webapp/WEB-INF/spring/appServlet/servlet-context.xml`](https://github.com/bexperr/template-spring-rest-mvc/blob/master/webapp/WEB-INF/spring/appServlet/servlet-context.xml)|

	<context:annotation-config></context:annotation-config>
	
	<!-- Contexto DispatcherServlet: defina la infraestructura de procesamiento de solicitudes de este servlet -->
	<context:component-scan base-package="com.bexperr.app" />
	
	
	<mvc:resources mapping="/resources/**" location="/resources/"  cache-period="31556926"/>
	
	<!-- Enables the Spring MVC @Controller programming model -->
	<mvc:annotation-driven></mvc:annotation-driven>

	<bean id="viewResolve"  class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/" ></property>
		<property name="suffix" value=".jsp" ></property>
	</bean>

## Configuracion Log4J

### [Dependencias Maven](https://github.com/bexperr/template-spring-rest-mvc/blob/master/pom.xml)

    		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

### [log4j2.properties](https://github.com/bexperr/template-spring-rest-mvc/blob/master/src/log4j2.properties "log4j2.properties")

El  archivo log4j2.properties se debe encontrar al nivel del ***src*** . 
***property.filename*** : Ruta donde se almacenara el archivo .log

    rootLogger.level = INFO
    property.filename = /bexperr/logger/plantilla/app.log
    appenders = R, console
    
    appender.console.type = Console
    appender.console.name = STDOUT
    appender.console.layout.type = PatternLayout
    appender.console.layout.pattern = %d{yyyy-MM-dd HH:mm:ss} %5p (%F:%L) - %m%n
    
    appender.R.type = RollingFile
    appender.R.name = File
    appender.R.fileName = ${filename}
    appender.R.filePattern = ${filename}.%d{yyyy-MM-dd}.log
    appender.R.layout.type = PatternLayout
    appender.R.layout.pattern = %d{yyyy-MM-dd HH:mm:ss} %5p (%F:%L) - %m%n
    appender.R.policies.type = Policies
    appender.R.policies.time.type = TimeBasedTriggeringPolicy
    appender.R.policies.time.interval = 1
    
    rootLogger.appenderRefs = R, console
    
    rootLogger.appenderRef.console.ref = STDOUT
    rootLogger.appenderRef.R.ref = File

### [Uso en Clases](https://github.com/bexperr/template-spring-rest-mvc/blob/master/src/com/bexperr/app/HomeController.java)



 

       import org.apache.logging.log4j.LogManager;
       import org.apache.logging.log4j.Logger;
       private  static  final  Logger logger =  LogManager.getLogger(NombreClase.class);
       logger.info("entrando al metodo");


 

[https://stackedit.io/](https://stackedit.io/)