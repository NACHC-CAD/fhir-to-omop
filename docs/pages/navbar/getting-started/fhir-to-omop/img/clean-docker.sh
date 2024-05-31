:: Stop all containers
docker container stop $(docker container list -aq)

:: Clean up unused objects
docker system prune

:: Remove all images, containers, and volumes
docker rm $(docker ps -aq)
docker rmi $(docker images -aq)
docker volume rm $(docker volume ls -q)

:: See if any images are left
docker images -a

:: See if any containers are left
docker ps -a

:: See if any volumes are left
docker volume ls