#!/bin/bash

SRC_DIR="src/main/java/org/example"
OUT_DIR="build/classes/java/main"
DOCS_DIR="build/docs/javadoc"
MAIN_CLASS="org.example.HeapSort"

mkdir -p "$OUT_DIR"
javac -d "$OUT_DIR" "$SRC_DIR"/*.java
javadoc -d "$DOCS_DIR" -sourcepath "$SRC_DIR" $MAIN_CLASS
java -classpath "$OUT_DIR" "$MAIN_CLASS"

