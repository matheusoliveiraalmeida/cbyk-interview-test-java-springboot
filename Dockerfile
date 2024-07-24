FROM openjdk:17-alpine

RUN apk update && apk upgrade \
    && apk add --no-cache freetype fontconfig ttf-opensans

RUN addgroup -g 1001 -S appuser && adduser -u 1001 -S appuser -G appuser
RUN mkdir /app && chown -R appuser:appuser /app

WORKDIR /app
USER appuser

COPY --chown=appuser:appuser . .

COPY target/*.jar contas-pagar.jar

CMD ["java", "-jar", "/app/contas-pagar.jar"]
