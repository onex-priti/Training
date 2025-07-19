#!/bin/bash

set -e  # exit on error

echo "Cleaning previous builds..."
mvn clean

echo "Installing dependencies and compiling..."
mvn install

echo "Running tests..."
mvn test

echo "Running the application..."
mvn exec:java -Dexec.mainClass="com.example.App"
