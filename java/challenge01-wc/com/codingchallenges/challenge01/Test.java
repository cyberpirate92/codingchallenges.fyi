package com.codingchallenges.challenge01;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Missing argument: filename");
            System.exit(1);
        }
        var file = new File(args[0]);
        if (!file.exists()) {
            System.err.println("File does not exist: " + file.getAbsolutePath());
            System.exit(1);
        }
        var bufferedReader = new BufferedInputStream(new FileInputStream(file));
        int currentByte;

        StringBuilder byteString = new StringBuilder();
        StringBuilder byteCountString = new StringBuilder();

        int skipCount = 0;
        System.out.println();
        while ((currentByte = bufferedReader.read()) != -1) {
            int byteCount = skipCount == 0 ? getByteCount(currentByte) : -1;
            if (byteCount != -1) {
                skipCount = byteCount;
            }
            byteString.append(String.format("%03d ", currentByte));
            if (byteCount == -1) {
                byteCountString.append(String.format("%3s ", "*"));
            } else {
                byteCountString.append(String.format("%3d ", byteCount));
            }
            skipCount--;
        }
        System.out.println(byteString);
        System.out.println(byteCountString);
    }

    /**
     * Determine the byte count given the first byte of a possibly multibyte character
     * Unicode characters can range anywhere from 1-4 bytes.
     * The first byte of a multibyte character can be used to determine the number of bytes that form the character.
     * If the binary representation of the first byte
     *      - starts with 1111 xxxx (240 - 247), then it's a 4 byte character
     *      - starts with 1110 xxxx (224 - 239), then it's a 3 byte character
     *      - starts with 1100 xxxx (192 - 223), then it's a 2 byte character
     * @param byteValue
     * @return number of bytes
     */
    private static int getByteCount(int byteValue) {
        if (byteValue >= 0 && byteValue <= 127) {
            return 1;
        } else if (byteValue >= 192 && byteValue <= 223) {
            return 2;
        } else if (byteValue >= 224 && byteValue <= 239) {
            return 3;
        } else if (byteValue >= 240 && byteValue <= 247) {
            return 4;
        } else {
            throw new IllegalArgumentException("Invalid byte value " + byteValue);
        }
    }
}
