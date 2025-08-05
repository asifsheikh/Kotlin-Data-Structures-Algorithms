#!/bin/bash

# Build script for Kotlin Data Structures & Algorithms project

echo "Building Kotlin Data Structures & Algorithms project..."

# Find all Kotlin source files
find src -name "*.kt" -type f > sources.txt

# Compile all source files
kotlinc @sources.txt -include-runtime -d app.jar

# Create manifest file with main class
echo "Main-Class: MainKt" > manifest.txt

# Create a new JAR with the manifest
jar cfm app.jar manifest.txt -C . .

# Clean up
rm manifest.txt

# Clean up
rm sources.txt

if [ $? -eq 0 ]; then
    echo "Build successful! Run with: java -jar app.jar"
else
    echo "Build failed!"
    exit 1
fi 