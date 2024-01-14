@REM @echo off 
docker compose down
docker network rm shared-network
docker network create shared-network --attachable --subnet 172.28.0.0/16
docker image prune --all --force
docker-compose up -d backend-factory backend-shop react-front

:loop
for /f %%i in ('curl --connect-timeout 2 -s -o NUL -w "%%{http_code}" http://localhost:8083/ws/status.wsdl') do set "status=%%i"
if not "%status%"=="200" (
    echo ..
    timeout /t 3 /nobreak > NUL
    goto :loop
)

echo Backend is up
curl -f http://localhost:8083/ws/status.wsdl > gateway/status.wsdl

docker-compose up -d backend-gateway
