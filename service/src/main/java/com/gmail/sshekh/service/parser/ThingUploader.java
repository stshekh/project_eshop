package com.gmail.sshekh.service.parser;

import com.gmail.sshekh.dao.model.Item;
import com.gmail.sshekh.service.model.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("thingUploader")
public class ThingUploader {

    private final ThingParser thingParser;
    private final ThingConverter thingConverter;

    @Autowired
    public ThingUploader(
            @Qualifier("thingParser") ThingParser thingParser,
            @Qualifier("thingConverter") ThingConverter thingConverter
    ) {
        this.thingParser = thingParser;
        this.thingConverter = thingConverter;
    }


    public List<Item> items() {
        List<Thing> things = thingParser.parse();
        return thingConverter.convert(things);
    }
}
