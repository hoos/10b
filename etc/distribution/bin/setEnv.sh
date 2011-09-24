# This script sets up the required environment
# to run the biex test suit.

# Make sure we don't get any CLASSPATH conflicts.
#unset CLASSPATH

unset ANT_HOME

export ANT_HOME=$BIN_DIR/thirdparty/apache-ant-1.8.2

export JAVA_HOME=/usr/java/latest

export PATH=$ANT_HOME/bin:$JAVA_HOME/bin:$PATH
