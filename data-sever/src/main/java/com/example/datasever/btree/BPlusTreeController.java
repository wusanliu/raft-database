package com.example.datasever.btree;

import com.example.datasever.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class BPlusTreeController {
    @Autowired
    private BPlusTreeService bPlusTreeService;

    @PostMapping("/insert")
    public R<String> insert(String key,String value){
        Boolean flag = bPlusTreeService.insert(key, value);
        if (!flag){
            return R.success("插入失败");
        }
        return R.success("插入成功");
    }

    @GetMapping("/search")
    public R<String> search(String key){
        String value = bPlusTreeService.search(key);
        if (value==null){
            return R.success("不存在该键值对");
        }
        return R.success(value);
    }
}
