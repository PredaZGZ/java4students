javac -d bin -cp .:lib/mysql-connector-java-9.2.0.jar ./src/*.java ./src/dao/*.java ./src/view/*.java ./src/model/*.java
java -cp "./bin;./lib/mysql-connector-java-9.2.0.jar" App