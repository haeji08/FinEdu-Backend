FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/summarizer-0.0.1-SNAPSHOT-plain.jar app.jar

# 환경 변수 설정
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=mysql_password
ENV OPENAI_API_KEY=openai_api_key
ENV SERVER_PORT=8081

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]