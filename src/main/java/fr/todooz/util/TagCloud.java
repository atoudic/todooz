package fr.todooz.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagCloud {
    private List<String> tags = new ArrayList<String>();

    public void add(String... tag) {
        if (!(tag == null)){
            for (String item : tag) {
                if (!(item == null)&& !(item == "") && !(contains(item))) {
                    tags.add(item);
                }
            }
        }
    }

    public int size() {
        return tags.size();
    }

    public boolean contains(String tag) {
        return tags.contains(tag);
    }

    public void top(int i) {
        if (i<0) tags = tags.subList(0, 0);
        else if (i < size())        tags = tags.subList(0,i);
    }

    public void shuffle() {
        Collections.shuffle(tags);

    }
}
