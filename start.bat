@REM @echo off 
docker-compose up -d
timeout /t 15
curl -f http://localhost:8083/ws/status.wsdl > gateway/status.wsdl
docker-compose -f docker-compose2.yaml up -d
