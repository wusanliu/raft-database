package com.example.datasever.btree;

import java.io.Serializable;

class KeyValuePair implements Serializable {
    private static final long serialVersionUID = 1L;
    String key;
    String value;

    KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

