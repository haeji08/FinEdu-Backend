# Base image 설정
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY build/libs/quiz-0.0.1-SNAPSHOT.jar app.jar

# 환경 변수 설정 (OpenAI 및 DB)
ENV OPENAI_API_KEY=open_api_key
ENV MYSQL_USER=finedu_user
ENV MYSQL_PASSWORD=finedu_password
ENV SERVER_PORT=8082

# 애플리케이션 실행
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]