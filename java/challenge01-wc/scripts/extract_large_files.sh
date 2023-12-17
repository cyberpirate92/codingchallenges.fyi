#!/bin/bash

TARGET_DIR="test_data"
COMPRESSED_FILE="$TARGET_DIR/large-files.tar.gz"

if [ ! -f $COMPRESSED_FILE ]; then
    echo "Compressed file not found"
    exit 1
fi

tar xvzf "$COMPRESSED_FILE" -C "$TARGET_DIR"