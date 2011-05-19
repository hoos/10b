#!/bin/sh
#
# build.sh     A build script that wraps Ant.
#
# Author:      Hussein Badakhchani (Hoos)
#
# Description: This script is used to compile and create distributions of
#              10b.  All the arguments that are supplied to this script
#              are passed on to Ant.

BIN_DIR=`/bin/env dirname $0`
APPLICATION_HOME=$BIN_DIR/../
ANT_HOME=$APPLICATION_HOME/thirdparty/apache-ant-1.8.2

PATH=$ANT_HOME/bin:$PATH

ant -buildfile $BIN_DIR/build.xml $*
