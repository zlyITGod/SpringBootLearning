package com.zly.sonelasticsearch;

import com.zly.sonelasticsearch.model.Item;
import com.zly.sonelasticsearch.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SonElasticsearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ItemRepository repository;

    @Test
    public void testCreateIndex(){
        template.createIndex(Item.class);
    }

    @Test
    public void insert() {
        Item item = new Item();
        item.setBrand("苹果");
        item.setCategory("嘻嘻嘻嘻嘻嘻");
        item.setId(111L);
        item.setImages("ssssssss");
        item.setPrice(6899.00);
        item.setTitle("手机");
        repository.save(item);
    }


    @Test
    public void contextLoads() {
    }

}
