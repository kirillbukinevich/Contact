1.Create database user:
	username: kirillbukinevich
	password: password
2.Execute src\main\resources\contact.sql file
3.Build war from console: mvn clean package
4.Copy .war file into the .../tomcat/webapp
5.Start tomcat
6.Run in browser: http://localhost:8080/zcontact/welcome.jsp