#!/bin/sh

cd tests
javac *.java
dx --dex --output=classes.dex *.class
zip ../tests.jar classes.dex
rm classes.dex *.class
cd ..
