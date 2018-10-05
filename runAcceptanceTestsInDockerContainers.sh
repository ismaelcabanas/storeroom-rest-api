#!/bin/bash
# runTestsInDockerContainers.sh
set -e

echo "Building Docker containers..."
docker-compose -f docker-compose-acceptance.yml up -d
echo "Waiting for Docker containers to finish setting up..."
./waitForContainerSetup.sh
docker-compose ps
echo "Run acceptance tests..."
./gradlew acceptanceTest
echo "Tests complete, stop and remove Docker containers..."
docker-compose down --remove-orphans