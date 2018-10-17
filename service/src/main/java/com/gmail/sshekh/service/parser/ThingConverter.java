package com.gmail.sshekh.service.parser;

import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.service.model.Thing;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component("thingConverter")
public class ThingConverter {

    public List<Item> convert(List<Thing> storeItems) {
        List<Item> items = new ArrayList<>();
        for (Thing element : storeItems) {
            Item item = new Item();
            item.setId(element.getId());
            item.setDescription(element.getDescription());
            item.setName(element.getName());
            item.setUniqueNumber("F"+String.valueOf(LocalDateTime.now()));
            item.setPrice(element.getPrice());
            item.setEnable(element.getAlive());
            items.add(item);
        }
        return items;
    }
}
