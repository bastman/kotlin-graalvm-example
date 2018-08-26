#!/usr/bin/env sh
set -e

# sdk use java 1.0.0-rc5-graal
alias native-image="$HOME/.sdkman/candidates/java/1.0.0-rc5-graal/bin/native-image"
java -version
native-image --version

GRAALVM_HOME="$HOME/.sdkman/candidates/java/1.0.0-rc5-graal"

MAIN_CLASS_NAME="com.example.demo.App"
COMPILER_SINK_FILE="build/helloworld"
GRADLE_COMMAND="./gradlew clean shadowJar"
GRADLE_SINK_JAR="./build/libs/kotlin-graalvm-example-1.0-SNAPSHOT-all.jar"

#OPTS=""
OPTS="-H:+ReportUnsupportedElementsAtRuntime -H:ReflectionConfigurationFiles=./reflection-001.json"
#OPTS="-H:ReflectionConfigurationFiles=./reflection.json -H:+ReportUnsupportedElementsAtRuntime -Dfile.encoding=UTF-8"
#-H:PrintFlags=Expert
#  --expert-options
#OPTS="-H:+ReportUnsupportedElementsAtRuntime -Dfile.encoding=UTF-8"

COMPILER_COMMAND="--verbose -cp ${GRADLE_SINK_JAR} -H:Name=${COMPILER_SINK_FILE} -H:Class=${MAIN_CLASS_NAME} ${OPTS}"


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
echo " "
echo "============================================="


# gradle build jar
set -ex
${GRADLE_COMMAND}

# compile to native binary
native-image ${COMPILER_COMMAND}

set -e +x

echo ""
echo "Done."
echo "You may want to run your compiled binary ..."
echo ""
echo "Example: $ ./${COMPILER_SINK_FILE}"
echo ""
