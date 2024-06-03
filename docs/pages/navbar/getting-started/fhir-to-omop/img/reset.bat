docker-compose down
docker-compose rm -f
docker image prune -a
docker volume rm rstudio-home-data rstudio-tmp-data
