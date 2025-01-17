FROM --platform=linux/arm64/v8 eclipse-temurin:17

WORKDIR /app

# backend 디렉토리의 파일들을 복사
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ gradle/
COPY common/ common/
COPY summarizer/ summarizer/

# Gradle 실행 권한 설정
RUN chmod +x ./gradlew

# 프로젝트 빌드
RUN ./gradlew :summarizer:build -x test

# JAR 파일 이름 변경
RUN mv summarizer/build/libs/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]