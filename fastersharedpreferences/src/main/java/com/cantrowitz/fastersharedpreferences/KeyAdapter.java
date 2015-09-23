package com.cantrowitz.fastersharedpreferences;

/**
 * Created by adamcantrowitz on 9/23/15.
 */
interface KeyAdapter {
    String adapt(String key);

    String original(String key);

    boolean isValid(String key);

    class FSPKeyAdapter implements KeyAdapter {
        private final int formattedPrefixLength;
        private final String formattedPrefix;

        public FSPKeyAdapter(String prefix) {
            this.formattedPrefix = String.format("%s_%s", prefix, "_");
            this.formattedPrefixLength = formattedPrefix.length();
        }

        @Override
        public String adapt(String key) {
            return formattedPrefix + key;
        }

        @Override
        public String original(String key) {
            if (isValid(key)) {
                return key.substring(formattedPrefixLength);
            } else {
                return key;
            }
        }

        @Override
        public boolean isValid(String key) {
            return key.startsWith(formattedPrefix);
        }
    }
}
