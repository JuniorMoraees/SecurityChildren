# Imagem base com Tomcat (Java 17 + Tomcat 9)
FROM tomcat:9.0-jdk17

# Removendo os arquivos de exemplo do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiando o .war gerado para a pasta webapps do Tomcat
COPY target/JMTech-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expondo a porta padrão do Tomcat
EXPOSE 8080

# Comando padrão para iniciar o Tomcat
CMD ["catalina.sh", "run"]