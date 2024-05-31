FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/QuizApplicationQuizService.v2-0.0.1-SNAPSHOT.jar QuizApplicationQuizService.v2.jar
ENTRYPOINT ["java","-jar","/QuizApplicationQuizService.v2.jar"]
EXPOSE 8083
