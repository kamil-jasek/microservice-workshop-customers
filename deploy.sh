#!/bin/sh
./mvnw clean compile kotlin:test-compile package
./mvnw -B release:update-versions
PROJECT_VERSION=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
docker build . -t "localhost:5000/customers:$PROJECT_VERSION"
docker push "localhost:5000/customers:$PROJECT_VERSION"
cat ./deployment/deployment.yaml > deployment.tmp.yaml
sed -i '' "s/{{version}}/$PROJECT_VERSION/g" deployment.tmp.yaml
kubectl apply \
  -f deployment/service-profile.yaml \
  -f deployment/service-account.yaml \
  -f deployment/service.yaml \
  -f deployment/server.yaml \
  -f deployment.tmp.yaml

rm deployment.tmp.yaml
kubectl rollout status deployment/customers -n microservices-workshop
