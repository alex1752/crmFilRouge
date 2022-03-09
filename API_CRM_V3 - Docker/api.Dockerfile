FROM tomcat:9.0.55-jdk16-temurin

COPY ./api-crm.war /usr/local/tomcat/webapps/api-crm.war

EXPOSE 8080

CMD ["catalina.sh", "run"]