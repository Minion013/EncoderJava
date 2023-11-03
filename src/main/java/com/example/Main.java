package com.example;

import com.google.common.primitives.Chars;
import org.apache.commons.lang3.ArrayUtils;
import java.util.*;

public class Main {

    public static String encode(String plainText) {
        String reference = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
        char[] referenceTable = reference.toCharArray();
        char[] plainTextArray = (plainText.toUpperCase()).toCharArray(); // change text to array
        char[] updatedReferenceTable = reference.toCharArray();
        int indexOfFirstLetter = Chars.indexOf(referenceTable, plainTextArray[0]); // get the difference in position vs
                                                                                   // original table
        ArrayUtils.shift(updatedReferenceTable, indexOfFirstLetter); // shift the reference table
        char[] newPlainTextArray = new char[plainTextArray.length]; // create new plaintext Array
        newPlainTextArray[0] = plainTextArray[0];
        for (int i = 1; i < plainTextArray.length; i++) {
            if (Character.isWhitespace(plainTextArray[i])) {
                newPlainTextArray[i] = ' ';
            } else {
                int indexOfLetter = Chars.indexOf(referenceTable, plainTextArray[i]);
                newPlainTextArray[i] = updatedReferenceTable[indexOfLetter];
            }
        }
        String encodedPlainText = new String(newPlainTextArray);
        return encodedPlainText;
    }

    public static String decode(String encodedText) {
        String reference = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
        char[] referenceTable = reference.toCharArray();
        char[] encodedPlainTextArray = (encodedText.toUpperCase()).toCharArray(); // change text to array
        char[] updatedReferenceTable = reference.toCharArray();
        int indexOfFirstLetter = Chars.indexOf(referenceTable, encodedPlainTextArray[0]); // get the difference in
                                                                                          // position vs original
                                                                                          // table
        ArrayUtils.shift(updatedReferenceTable, (~(indexOfFirstLetter - 1))); // shift the reference table
        char[] newPlainTextArray = new char[encodedPlainTextArray.length]; // create new plaintext Array
        newPlainTextArray[0] = encodedPlainTextArray[0]; // adding first letter back to array
        for (int i = 1; i < encodedPlainTextArray.length; i++) {
            if (Character.isWhitespace(encodedPlainTextArray[i])) {
                newPlainTextArray[i] = ' ';
            } else {
                int indexOfLetter = Chars.indexOf(referenceTable, encodedPlainTextArray[i]);
                newPlainTextArray[i] = updatedReferenceTable[indexOfLetter];
            }

        }
        String plainText = new String(newPlainTextArray);
        return plainText;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string you wish to encode/decode: ");
        String userInput = sc.nextLine();
        System.out.print("Encode/Decode? ");
        String userOption = sc.nextLine();
        if (userOption.equalsIgnoreCase("Encode")) {
            String encodedString = encode(userInput);
            System.out.print(encodedString);
        } else if (userOption.equalsIgnoreCase("Decode")) {
            String decodedString = decode(userInput);
            System.out.print(decodedString);
        } else {
            System.out.print("Wrong inputs");
        }
        sc.close();
    }

}