#!/bin/bash

echo "🔧 Compilando projeto..."
mvn -v
mvn clean package

echo "🚚 Copiando WAR para instância local do Tomcat..."
cp target/student-system.war .tomcat/webapps/

echo "🚀 Iniciando Tomcat local..."
.tomcat/bin/startup.sh