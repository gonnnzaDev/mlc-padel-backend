#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"


if ss -tlnp 2>/dev/null | grep -q ':8080'; then
    pkill -f "java -jar target/mlc-backend" 2>/dev/null || true
    sleep 2
fi

bash mvnw -q package -DskipTests

exec java -jar target/mlc-backend-0.0.1-SNAPSHOT.jar
