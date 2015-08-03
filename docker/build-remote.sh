MONGO_STARTED=$(docker ps -q --filter="name=mongo")
echo "Mongo container id: $MONGO_STARTED"
if [ -z "$MONGO_STARTED" ];
then
	echo "Mongo container isn't started"
	if [ -n "$(docker images | grep mongo)" ];
	then
		echo "Mongo image is being pulled"
		docker pull mongo
	else
		docker images | grep mongo
	fi
	echo "Start container"
	mkdir /root/db/
	docker run -v /root/db/:/data/db/ --restart="always" --name mongo -d mongo
fi
NAMSTERDAM_CONT=$(docker ps -q --filter="name=cards")
echo "Namsterdam container id: $NAMSTERDAM_CONT"
if [ -n "$NAMSTERDAM_CONT" ];
then
echo "Namsterdam stopping"
docker stop --time=10 $NAMSTERDAM_CONT
fi
docker rm namsterdam
gunzip < /tmp/cards.zip | docker load
docker run --restart="always" -p 80:8080 --link mongo:mongo -d --name cards  cards