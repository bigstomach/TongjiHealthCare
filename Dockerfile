FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER bigstomach <bigstomach07@gmail.com>
# 工作目录在镜像的 /build 目录下
WORKDIR /build/
# 把本地的 pom.xml 和 src目录 复制到镜像的/build目录下
COPY pom.xml /build/
COPY src /build/src/
# 执行maven打包
RUN mvn package
# 运行jar采用jdk基础镜像
FROM openjdk:8-jdk-alpine
# 设置工作目录在镜像的 /app 目录下
WORKDIR /app
# 将jar包添加到容器中 /app 目录下
COPY --from=MAVEN_BUILD /build/target/tongjihealthcare-0.0.1-SNAPSHOT.jar /app/
# 运行jar包
ENTRYPOINT ["java","-jar","tongjihealthcare-0.0.1-SNAPSHOT.jar"]