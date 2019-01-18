#!/bin/bash

tomcat_home=/usr/local/tomcat-8080

cd `dirname $0`
basepath=$(cd `dirname $0`; pwd)
svn up --username=eugene --password=eugene@cQap4Pdf --no-auth-cache

mvn clean -P test package --llr -DskipTests
pid=`ps -ef|grep '/usr/local/tomcat-8080/conf' |grep -v "grep"|awk -F ' ' '{print $2}'`
kill -9 $pid

rm -rf ${tomcat_home}/webapps/ditui
rm -rf ${tomcat_home}/webapps/ditui.war
cp ditui-server/target/ditui.war ${tomcat_home}/webapps/
${tomcat_home}/bin/startup.sh
tail -f ${tomcat_home}/logs/catalina.out

