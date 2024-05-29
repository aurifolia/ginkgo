FROM harbor.local/library/eclipse-temurin:21.0.2_13-jre
LABEL maintainer=aurifolia
WORKDIR /user/share/${SERVICE_NAME}
COPY target/${PACKAGE_NAME} .
CMD ["java", "$JAVA_OPTS",  "-jar", "${PACKAGE_NAME}"]