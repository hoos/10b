#!/bin/sh
#
# build.sh     A build script that wraps Ant.
#
# Author:      Hussein Badakhchani (Hoos)
#
# Description: This script is used to compile and create distributions of
#              10b.  All the arguments that are supplied to this script
#              are passed on to Ant.

APPLICATION_HOME=`/bin/env dirname $0`
ANT_HOME=$APPLICATION_HOME/thirdparty/apache-ant-1.8.2

$ANT_HOME/bin/ant -buildfile $APPLICATION_HOME/build.xml $*
