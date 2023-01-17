## Local tests 

Run app for dev purpose by 
```shell
mvn clean compile quarkus:dev
```

and run test by
```shell
docker run --network="host" --rm -i grafana/k6 run - <search.js
```