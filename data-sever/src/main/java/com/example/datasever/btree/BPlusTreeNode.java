package com.example.datasever.btree;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BPlusTreeNode implements Serializable{
    private static final long serialVersionUID = 1L;
    boolean isLeaf;
    List<KeyValuePair> keyValuePairs;
    List<BPlusTreeNode> children;

    public BPlusTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keyValuePairs = new ArrayList<>();
        this.children = new ArrayList<>();
    }
}
