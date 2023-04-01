package com.baol.hashing;

public class HashTable {

    private final int INITIAL_SIZE = 16;
    private final HashEntry[] entries = new HashEntry[this.INITIAL_SIZE];

    public void put(final String key, final Object value) {
        final int hash = this.getHash(key); // Position in array.
        final HashEntry newEntry = new HashEntry(key, value);

        // When there is no entry in the position,
        // that position can be occupied by the new
        // entry immediately.
        if (this.entries[hash] == null) {
            this.entries[hash] = newEntry;
            return;
        }

        HashEntry entry = entries[hash];
        // Linearly iterate through the array to find
        // an available position to put the new entry.
        while (entry.hasNext()) {
            entry = entry.next;
        }
        entry.next = newEntry;
    }

    public Object get(final String key) throws NoSuchKeyException {
        final int hash = this.getHash(key);
        HashEntry entry = this.entries[hash];

        if (entry == null) {
            throw new NoSuchKeyException(key);
        }

        // Iterate over the chain position
        // to find the correct key.
        while (!entry.key.equals(key)
                && entry.hasNext()) {
            entry = entry.next;
        }

        // If iteration is over, but the
        // correct key is not found, throw
        // a NoSuchKeyException because key
        // does not exist in the HashTable.
        if (!entry.key.equals(key)) {
            throw new NoSuchKeyException(key);
        }

        // Return the value of the entry
        // if the key was found.
        return entry.value;
    }

    private int getHash(final String key) {
        int sum = 0;
        for (char c : key.toCharArray()) {
            sum += c;
        }
        return sum % this.INITIAL_SIZE;
    }

    private static class HashEntry {

        private final String key;
        private final Object value;
        private HashEntry next = null; // For closed addressing, chaining.

        private HashEntry(final String key, final Object value) {
            this.key = key;
            this.value = value;
        }

        private boolean hasNext() {
            return this.next != null;
        }
    }

    static class NoSuchKeyException extends Exception {

        private final String key;

        NoSuchKeyException(final String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
    }
}
