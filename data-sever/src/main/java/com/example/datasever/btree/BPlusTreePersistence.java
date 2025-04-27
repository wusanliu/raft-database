package com.example.datasever.btree;

import java.io.*;


class BPlusTreePersistence {

    public static void saveToFile(BPlusTree tree, String filename)  {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(tree);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BPlusTree loadFromFile(String filename) {
        File file = new File(filename);
        if (file.length()==0){
            return new BPlusTree();
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            return (BPlusTree) in.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
