#!/usr/bin/env bash
set -e
docker compose build
docker compose up -d
echo "Frontend http://localhost:5173  Backend http://localhost:8080"
