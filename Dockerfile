FROM openjdk:8 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/myapp/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat testng.xml $APP_HOME
COPY gradle $APP_HOME/gradle
# download dependencies
#RUN ./gradlew build 
#COPY . .
#RUN ./gradlew build

#RUN ./gradlew build
#FROM openjdk:8-jre
#WORKDIR /root/
#COPY --from=BUILD_IMAGE /root/dev/myapp/build/libs/myapp.jar .
#EXPOSE 8080
#CMD ["java","-jar","myapp.jar"]
