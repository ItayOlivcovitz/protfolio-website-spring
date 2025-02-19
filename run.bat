@echo off
REM Build the Docker image and tag it as "growhire-site"
echo Building Docker image...
docker build -t growhire-site .
if errorlevel 1 (
    echo Failed to build Docker image.
    pause
    exit /b 1
)

REM Optionally stop and remove an existing container named "growhire-site-container"
echo Stopping and removing any existing container named "growhire-site-container"...
docker rm -f growhire-site-container >nul 2>&1

REM Run the Docker container in detached mode, mapping port 8080 on host to port 8080 in the container
echo Running Docker container...
docker run -d -p 8080:8080 --name growhire-site-container growhire-site
if errorlevel 1 (
    echo Failed to run Docker container.
    pause
    exit /b 1
)

echo Docker container started successfully.
pause
