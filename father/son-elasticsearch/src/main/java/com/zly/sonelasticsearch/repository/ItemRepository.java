package com.zly.sonelasticsearch.repository;

import com.zly.sonelasticsearch.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
}
