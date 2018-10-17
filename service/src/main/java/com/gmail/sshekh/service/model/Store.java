package com.gmail.sshekh.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "store")
public class Store {

    @XmlElement(name = "thing")
    private List<Thing> things = new ArrayList<>();

    public Store() {
        // Only for parsing items
    }

    public List<Thing> getItems() {
        return things;
    }

    public void setItems(List<Thing> items) {
        this.things = items;
    }
}
