#!/bin/sh

CONFIG_NAME=active

if [ $# -lt 2 ]
then
  echo "./stdrunserv.sh muxport offport [config_name]"
  echo
  echo "muxport should match the port that tcpmux on the phone is connecting to"
  echo "offport can be any unused port"
  exit 0
fi

if [ $# -gt 3 ]
then
  CONFIG_NAME=$3
fi

MUXPORT=$1
OFF_LISTEN_PORT=$2

. server_env/$CONFIG_NAME/ENV
BOOTCLASSPATH=$BOOTCLASSPATH
ANDROID_CACHE=`pwd`/cache
ANDROID_DATA=`pwd`/data
ANDROID_ROOT=`pwd`/system
LD_LIBRARY_PATH=`pwd`/system/lib
OFF_SERVER=1
OFFLOAD_DEX_CACHE=`pwd`/data/off-cache
export BOOTCLASSPATH ANDROID_CACHE ANDROID_DATA ANDROID_ROOT LD_LIBRARY_PATH OFF_SERVER OFFLOAD_DEX_CACHE OFF_LISTEN_PORT

# This library was not working correctly
#rm -f system/lib/libicuuc.so

#valgrind system/bin/dalvikvm -Xverify:none -Xdexopt:all -Xgc:precise -Xgenregmap $@
#gdb --args system/bin/dalvikvm -Xdexopt:all -Xgc:precise -Xgenregmap $@

#system/bin/tcpmux --demux $MUXPORT 127.0.0.1:$OFF_LISTEN_PORT &
system/bin/dalvikvm -Xdexopt:all -Xgc:precise -Xgenregmap -Xmx100000k $@
kill -SIGINT %1
