#!/bin/bash

export DOCKER_BUILDKIT=1

docker build -f output-limit.Dockerfile --progress=plain --no-cache .