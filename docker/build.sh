REMOTE_HOST=$1
IMAGE_NAME="cards"
docker build -no-cache -t $IMAGE_NAME .
docker save $IMAGE_NAME | gzip | pv > /tmp/img.zip
scp ./build-remote.sh $REMOTE_HOST:~/build-remote.sh
scp /tmp/img.zip $REMOTE_HOST:/tmp/$IMAGE_NAME.zip
ssh $REMOTE_HOST 'chmod +x ~/build-remote.sh && ~/build-remote.sh'