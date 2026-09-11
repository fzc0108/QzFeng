# 基础镜像
FROM openjdk:17.0.2-slim

# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建目录
RUN mkdir -p /app && mkdir -p /data/uploads

# 复制 jar 包到容器里
COPY app.jar /app/app.jar

# 启动入口
ENTRYPOINT ["java", "-jar", "/app/app.jar"]