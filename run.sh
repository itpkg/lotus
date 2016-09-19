#!/bin/sh

JAVA_HOME=/opt/jdk8
PATH=$JAVA_HOME:$PATH
export JAVA_HOME PATH

java -cp 'lotus-app-2016.9.18.jar:config' org.springframework.boot.loader.JarLauncher
