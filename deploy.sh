#!/bin/bash

function trap_ctrlc() {
	echo "Shutting down docker."
	sudo docker-compose down
	echo "Stopped bash opeation."
	exit 2
}

trap "trap_ctrlc" 2

cd challenge-birras-santander-login &&
 sudo mvn clean package -DskipTests &&
cd ../ &&
 cd challenge-birras-santander-meet-up &&
 sudo mvn clean package -DskipTests &&
 cd ../ &&
 sudo docker-compose -f docker-compose.yml up -d