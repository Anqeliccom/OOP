#!/bin/bash

SRC="src/main/java/org/example/HeapSort.java"
BIN_DIR="bin"
DOC_DIR="docs"

javac -d $BIN_DIR $SRC
javadoc -d $DOC_DIR -sourcepath src/main/java org.example
java -classpath $BIN_DIR org.example.HeapSort

