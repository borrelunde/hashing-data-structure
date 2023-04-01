package com.baol.hashing;

public class Main {

    public static void main(String[] args) {

        final HashTable hashTable = new HashTable();
        final String[] names = {
                "George", "James", "Richard", "Tanya", "Lisa", "Sara",
                "Owen", "Edward", "Simon", "Juliet", "Beatrice", "Mia"
        };

        // Populate hash table.
        for (String name : names) {
            hashTable.put(name.toLowerCase(), new Person(name));
        }

        // Try to get Derek in the hash table and print his name.
        try {
            System.out.println(hashTable.get("Derek"));
        } catch (HashTable.NoSuchKeyException e) {
            System.out.printf("There is no entry of key \"%s\".%n", e.getKey());
        }
    }
}
