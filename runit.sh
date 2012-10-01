#!/bin/sh

. server_env/active/ENV
BOOTCLASSPATH=$BOOTCLASSPATH:`pwd`/tests.jar
ANDROID_CACHE=`pwd`/cache
ANDROID_DATA=`pwd`/data
ANDROID_ROOT=`pwd`/system
LD_LIBRARY_PATH=`pwd`/system/lib
REMOTE_SERVER=localhost #rhythm.eecs.umich.edu
ENABLE_OFFLOAD=1

export BOOTCLASSPATH ANDROID_CACHE ANDROID_DATA ANDROID_ROOT LD_LIBRARY_PATH REMOTE_SERVER ENABLE_OFFLOAD

# This library was not working correctly
#rm -f system/lib/libicuuc.so

system/bin/dalvikvm -Xdexopt:all -Xgc:precise -Xgenregmap $@
