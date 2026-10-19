#!/usr/bin/env sh
# Gradle Wrapper script
# This script launches Gradle using the wrapper JAR.
# It is compatible with the JDK provided in the CI environment.

# Resolve the location of this script.
#!/usr/bin/env sh
# Gradle Wrapper script using JDK 17

# Resolve script location
APP_HOME=$(cd "$(dirname "$0")"; pwd)
# Resolve any symlinks
while [ -h "$APP_HOME/gradlew" ]; do
  LINK=$(readlink "$APP_HOME/gradlew")
  if [ "${LINK:0:1}" = "/" ]; then
    APP_HOME=$(dirname "$LINK")
  else
    APP_HOME=$(cd "$APP_HOME/$(dirname "$LINK")" && pwd)
  fi
done

# Explicit JDK 17 path
JAVA_HOME=$(brew --prefix openjdk@17)/libexec/openjdk.jdk/Contents/Home
DEFAULT_JVM_OPTS=""

# Execute Gradle Wrapper JAR with explicit java
exec "$JAVA_HOME/bin/java" $DEFAULT_JVM_OPTS -cp "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"