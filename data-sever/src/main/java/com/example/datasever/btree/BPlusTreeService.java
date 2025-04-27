package com.example.datasever.btree;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Slf4j
public class BPlusTreeService {

    public Boolean insert(String key,String value) {
        BPlusTree bPlusTree= null;
        log.info("0");
        System.out.println("0");
        bPlusTree = BPlusTreePersistence.loadFromFile("D:\\projectdata\\data.txt");
        log.info(bPlusTree.toString());
        log.info("1");
        bPlusTree.insert(key,value);
        log.info("2");
        BPlusTreePersistence.saveToFile(bPlusTree,"D:\\projectdata\\data.txt");
        log.info("3");
        return true;
    }
    public String search(String key)  {
        BPlusTree bPlusTree= null;
        bPlusTree = BPlusTreePersistence.loadFromFile("D:\\projectdata\\data.txt");
        return bPlusTree.search(key);
    }

}
