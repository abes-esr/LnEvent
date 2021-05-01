#!/bin/sh
set -e

if [ ! -e /app/classes/application-test.properties ]; then
        cp /app/classes/* /app/.
fi
