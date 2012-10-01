#!/bin/sh

. server_env/active/ENV
OFF_LISTEN_PORT=$1
BOOTCLASSPATH=$BOOTCLASSPATH:`pwd`/tests.jar
ANDROID_CACHE=`pwd`/cache
ANDROID_DATA=`pwd`/data
ANDROID_ROOT=`pwd`/system
LD_LIBRARY_PATH=`pwd`/system/lib
OFF_SERVER=1
OFFLOAD_DEX_CACHE=`pwd`/data/off-cache
export BOOTCLASSPATH ANDROID_CACHE ANDROID_DATA ANDROID_ROOT LD_LIBRARY_PATH OFF_SERVER OFFLOAD_DEX_CACHE OFF_LISTEN_PORT

# This library was not working correctly
#rm -f system/lib/libicuuc.so

system/bin/dalvikvm -Xdexopt:all -Xgc:precise -Xgenregmap $@
