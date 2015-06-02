package com.thepit.ipitslo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by DrIce on 5/19/15.
 */
public class SingletonListBeltEntry {

    private HashMap<String, ArrayList<BeltEntry>> map;
    /**
     * Using HashSet for efficiency when checking for duplicates - a contains operation with this is O(1) time as opposed to an
     * ArrayList contains which is O(n)
     */
    private HashSet<BeltEntry> itemHashSet;

    private static SingletonListBeltEntry singletonListBeltEntry;

    private SingletonListBeltEntry() {
        map = new HashMap<String, ArrayList<BeltEntry>>();
        itemHashSet = new HashSet<BeltEntry>();
    }

    public static SingletonListBeltEntry get() {
        if(singletonListBeltEntry == null) {
            singletonListBeltEntry = new SingletonListBeltEntry();
        }
        return singletonListBeltEntry;
    }
    /**
     * Add one item to structures of this singleton if it does not already contain it, including
     * hashmap which places item in there using it's "type" field as the key
     * @param item
     * @param type
     */
    public void addListviewItem(BeltEntry item, String type) {
        if(!itemHashSet.contains(item)) {
            itemHashSet.add(item);
            if(map.containsKey(type)) {
                map.get(type).add(item);
            } else {
                ArrayList<BeltEntry> newList = new ArrayList<BeltEntry>();
                newList.add(item);
                map.put(type, newList);
            }
        }
    }

    /**
     * add entire collection to structures of this singleton
     * @param itemList
     */
    public void addListviewItemList(Collection<BeltEntry> itemList, String beltGroup) {
        for(BeltEntry item : itemList) {
            addListviewItem(item, beltGroup);
        }
    }

    public void deleteListviewItem(BeltEntry item) {
        itemHashSet.remove(item);
        //TODO remove from map too
    }

    public ArrayList<BeltEntry> getCompleteList() {
        ArrayList<BeltEntry> items = new ArrayList<BeltEntry>();
        for(BeltEntry entry : itemHashSet) {
            items.add(entry);
        }
        return items;
    }

    /**
     * retrieve list of content for one given type from hashmap - used in non-all tabs/fragments
     * @param type
     * @return
     */
    public ArrayList<BeltEntry> getListFromMap(String type) {
        if(map.containsKey(type)) {
            return map.get(type);
        }
        return null;
    }
}
