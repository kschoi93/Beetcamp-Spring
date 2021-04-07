<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<div class="container">
	<img src="<%=request.getContextPath()%>/resources/6.jpg"/>
</div>
</body>
</html>

<!-- 
	springìì dbcpì¤ì 
	
	1. ojdbc8.jarì WEB-INF/lib 추가
	2. build path 추가
	
	3. pom.xml dependency 추가
				<!-- ojdbc8
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc8</artifactId>
			<version>11.2.0.1.0</version>
			<scope>system</scope>
			<systemPath>${basedir}/src/main/webapp/WEB_INF/lib/ojdbc8.jar</systemPath>
		</dependency>        
		<!-- spring jdbc -->
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<!-- 
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>5.2.6.RELEASE</version>
		</dependency>
		 
	4. servlet-context.xml 추가
	
	<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDeriver"></beans:property>
		<beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"></beans:property>
		<beans:property name="username" value="c##scott"></beans:property>
		<beans:property name="password" value="tiger"></beans:property>
	</beans:bean>
	
 -->