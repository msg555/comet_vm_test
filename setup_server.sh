#!/bin/sh 

if [ $# -eq 0 ]
then
  echo "./setup_server.sh config_name [prepare]"
  echo
  echo "Run once with phone connected via USB to grab needed files. e.g."
  echo "./setup_server.sh myphone prepare"
  echo
  echo "In the future reload this setup with"
  echo "./setup_server.sh myphone"
  exit 0
fi

if [ $# -eq 1 ]
then
  ENV_TYPE=$1
  if [ -d $ENV_TYPE ]
  then
    rm -f server_env/active
    ln -s $ENV_TYPE server_env/active
    exit 0
  else
    echo "No environment $ENV_TYPE found.  Try"
    echo "$0 $1 prepare"
    exit 1
  fi
fi

if [ $2 != "prepare" ]
then
  echo "Expected second argument to be 'prepare'"
  exit 1
fi

DIR=`pwd`/server_env/$1
mkdir -p $DIR

FIRST="y"
echo -n "BOOTCLASSPATH=" > $DIR/ENV
for JARF in $(adb shell 'echo -n $BOOTCLASSPATH' | sed 's/:/ /g')
do
  JARF_COPY=`echo $JARF | sed 's/\//_/g'`
  adb pull $JARF $DIR/$JARF_COPY
  if [ $FIRST != "y" ]
  then
    echo -n ":" >> $DIR/ENV
  fi
  echo -n $DIR/$JARF_COPY >> $DIR/ENV
  FIRST="n"
done
echo >> $DIR/ENV

rm -f server_env/active
ln -s $DIR server_env/active
