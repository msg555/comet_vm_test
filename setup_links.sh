#!/bin/sh

mkdir -p system cache/dalvik-cache data/dalvik-cache data/off-cache
ln -s ../../out/host/linux-x86/lib system/lib
ln -s ../../out/host/linux-x86/bin system/bin
ln -s ../../out/host/linux-x86/usr system/usr
