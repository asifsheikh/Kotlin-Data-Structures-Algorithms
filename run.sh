#!/bin/bash

# Run script for Kotlin Data Structures & Algorithms project

echo "Running Kotlin Data Structures & Algorithms project..."

# Find all Kotlin source files
find src -name "*.kt" -type f > sources.txt

# Compile and run directly
kotlinc @sources.txt -include-runtime -d app.jar && java -cp app.jar MainKt

# Clean up
rm sources.txt 