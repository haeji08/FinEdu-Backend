## Java 실행 환경
#FROM openjdk:17-jdk-slim
#
## 작업 디렉터리 설정
#WORKDIR /app
#
## JAR 파일 복사
#COPY build/libs/summarizer-0.0.1-SNAPSHOT.jar /app/summarizer.jar
#
## Cron 설치
#RUN apt-get update && apt-get install -y cron
#
## Cron Job 추가
#RUN echo "0 * * * * /usr/local/openjdk-17/bin/java -jar /app/summarizer.jar --spring.batch.job.names=newsSummarizationJob >> /var/log/cron.log 2>&1" > /etc/cron.d/summarizer-cron \
#    && chmod 0644 /etc/cron.d/summarizer-cron \
#    && crontab /etc/cron.d/summarizer-cron
#
## 로그 파일 생성
#RUN mkdir -p /var/log && touch /var/log/cron.log
#
## Cron 실행 및 로그 출력
#CMD ["bash", "-c", "cron && tail -f /var/log/cron.log"]

# Base image 설정
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY build/libs/summarizer-0.0.1-SNAPSHOT.jar app.jar

# 환경 변수 설정 (OpenAI 및 DB)
ENV OPENAI_API_KEY=open_api_key
ENV MYSQL_USER=finedu_user
ENV MYSQL_PASSWORD=finedu_password
ENV SERVER_PORT=8081

# 애플리케이션 실행
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]