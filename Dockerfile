FROM bellsoft/liberica-openjdk-debian:22

# Install required tools and GTK for JavaFX
RUN apt-get update && apt-get install -y \
    wget unzip libgtk-3-0 libxext6 libxrender1 libxtst6 libxi6 libgl1 \
    && rm -rf /var/lib/apt/lists/*

# JavaFX SDK setup
ARG JAVAFX_VERSION=22.0.1
ARG OS_ARCH=linux-x64
RUN wget https://download2.gluonhq.com/openjfx/${JAVAFX_VERSION}/openjfx-${JAVAFX_VERSION}_${OS_ARCH}_bin-sdk.zip -O /tmp/javafx-sdk.zip && \
    unzip /tmp/javafx-sdk.zip -d /opt && \
    rm /tmp/javafx-sdk.zip
RUN apt-get update && apt-get install -y libgl1 libxrender1 libxtst6 libxi6

# Set env vars
ENV DISPLAY=host.docker.internal:0
ENV PATH="/opt/javafx-sdk-${JAVAFX_VERSION}/bin:${PATH}"

# Copy app
WORKDIR /app
COPY target/AutoAxis-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

# Run the app
CMD ["java", "--module-path", "/opt/javafx-sdk-22.0.1/lib", "--add-modules", "javafx.controls,javafx.fxml", "-Djava.library.path=/opt/javafx-sdk-22.0.1/lib", "-jar", "app.jar"]

