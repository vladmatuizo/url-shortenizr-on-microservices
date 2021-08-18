@echo off

pushd "%~dp0\.."

call mvn clean install -T 2C
call docker-compose build
call docker-compose up

popd