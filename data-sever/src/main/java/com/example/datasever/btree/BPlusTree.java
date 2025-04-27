package com.example.datasever.btree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BPlusTree implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int T = 2; // 最小度数
    private BPlusTreeNode root;

    public BPlusTree() {
        this.root = new BPlusTreeNode(true);
    }

    // 插入键值对
    public void insert(String key, String value) {
        BPlusTreeNode r = root;
        if (r.keyValuePairs.size() == (2 * T - 1)) {
            BPlusTreeNode s = new BPlusTreeNode(false);
            root = s;
            s.children.add(r);
            splitChild(s, 0, r);
            insertNonFull(s, key, value);
        } else {
            insertNonFull(r, key, value);
        }
    }

    private void splitChild(BPlusTreeNode parent, int index, BPlusTreeNode fullNode) {
        BPlusTreeNode newNode = new BPlusTreeNode(fullNode.isLeaf);
        for (int i = 0; i < T - 1; i++) {
            newNode.keyValuePairs.add(fullNode.keyValuePairs.remove(T));
        }
        if (!fullNode.isLeaf) {
            for (int i = 0; i < T; i++) {
                newNode.children.add(fullNode.children.remove(T));
            }
        }
        parent.children.add(index + 1, newNode);
        parent.keyValuePairs.add(index, fullNode.keyValuePairs.remove(T - 1));
    }

    private void insertNonFull(BPlusTreeNode node, String key, String value) {
        int i = node.keyValuePairs.size() - 1;
        KeyValuePair newKeyValue = new KeyValuePair(key, value);

        if (node.isLeaf) {
            while (i >= 0 && key.compareTo(node.keyValuePairs.get(i).key) < 0) {
                i--;
            }
            node.keyValuePairs.add(i + 1, newKeyValue);
        } else {
            while (i >= 0 && key.compareTo(node.keyValuePairs.get(i).key) < 0) {
                i--;
            }
            i++;
            if (node.children.get(i).keyValuePairs.size() == (2 * T - 1)) {
                splitChild(node, i, node.children.get(i));
                if (key.compareTo(node.keyValuePairs.get(i).key) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key, value);
        }
    }

    // 查询方法
    public String search(String key) {
        return search(root, key);
    }

    private String search(BPlusTreeNode node, String key) {
        int i = 0;
        while (i < node.keyValuePairs.size() && key.compareTo(node.keyValuePairs.get(i).key) > 0) {
            i++;
        }
        if (i < node.keyValuePairs.size() && key.equals(node.keyValuePairs.get(i).key)) {
            return node.keyValuePairs.get(i).value;
        } else if (node.isLeaf) {
            return null;
        } else {
            return search(node.children.get(i), key);
        }
    }
}

