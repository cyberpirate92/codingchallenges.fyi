#!/bin/bash
JAR_FILE="out/artifacts/challenge01_wc_jar/challenge01-wc.jar"

if [ ! -f "$JAR_FILE" ]; then
    echo "Jar file not found. Please build the project first."
    exit 1
fi

java -jar "$JAR_FILE" "$@"