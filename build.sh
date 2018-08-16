#!/usr/bin/env bash
set -e

GRAALVM_HOME="$HOME/.sdkman/candidates/java/1.0.0-rc5-graal"
MAIN_CLASS_NAME="com.example.demo.App"
COMPILER_SINK_FILE="build/helloworld"
COMPILER="$GRAALVM_HOME/bin/native-image"
GRADLE_COMMAND="./gradlew clean build"
GRADLE_SINK_JAR="./build/libs/kotlin-graalvm-example-1.0-SNAPSHOT.jar"
COMPILER_COMMAND="$COMPILER -cp ${GRADLE_SINK_JAR} -H:Name=${COMPILER_SINK_FILE} -H:Class=${MAIN_CLASS_NAME} -H:+ReportUnsupportedElementsAtRuntime"

echo "===== build & compile to native binary .... ===="
echo ""
echo " STEP: build jar ... -> ${GRADLE_SINK_JAR}"
echo ""
echo " sink:"
echo "   - jar: ${GRADLE_SINK_JAR}"
echo " processor:"
echo "   - command: ${GRADLE_COMMAND}"
echo ""
echo " STEP: compile to native binary ... ${GRADLE_SINK_JAR} -> ${COMPILER_SINK_FILE}"
echo ""
echo " source:"
echo "   - jar: ${GRADLE_SINK_JAR}"
echo " sink:"
echo "   - binary file: ${COMPILER_SINK_FILE}"
echo "   - main class name: ${MAIN_CLASS_NAME}"
echo " processor:"
echo "   - graalvm home: $GRAALVM_HOME"
echo "   - compiler: $COMPILER"
echo " "
echo "============================================="

# check compiler exists
ls -la $COMPILER > /dev/null

# gradle build jar
set -ex
${GRADLE_COMMAND}

# compile to native binary
${COMPILER_COMMAND}
