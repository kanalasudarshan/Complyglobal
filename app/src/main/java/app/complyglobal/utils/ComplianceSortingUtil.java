package app.complyglobal.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import app.complyglobal.dto.ComplianceDTO;
import app.complyglobal.dto.EventItem;
import app.complyglobal.dto.HeaderItem;
import app.complyglobal.dto.ListItem;
import app.complyglobal.dummy.DummyContent;



/**
 * Created by SUDARSHAN REDDY on 29-10-2017.
 */

public class ComplianceSortingUtil {

    public static List<ListItem> buildList(List<ComplianceDTO> json, final int sortingTypeId){
        json.sort(new Comparator<ComplianceDTO>() {
            @Override
            public int compare(ComplianceDTO dto_1, ComplianceDTO dto_2) {
                switch (sortingTypeId){
                    case 1:
                        return dto_1.getDueDate().compareTo(dto_2.getDueDate());
                    case 2:
                        return dto_1.getDueDate().compareTo(dto_2.getDueDate());
                    case 3:
                        return dto_1.getDueDate().compareTo(dto_2.getDueDate());
                    case 4:
                        return dto_1.getDueDate().compareTo(dto_2.getDueDate());
                    case 5:
                        return dto_1.getDueDate().compareTo(dto_2.getDueDate());
                    case 6:
                        return dto_1.getDueDate().compareTo(dto_2.getDueDate());
                    default:
                        return dto_1.getDueDate().compareTo(dto_2.getDueDate());
                }
            }
        });


        TreeMap<String,List<ComplianceDTO>> treeMap=new TreeMap<>();
        for(int i=0;i<json.size();i++){
            ComplianceDTO item=json.get(i);
            if(treeMap.containsKey(item.getDueDateString())){
                List<ComplianceDTO> listItem=treeMap.get(item.getDueDateString());
                listItem.add(item);
                treeMap.put(item.getDueDateString(),listItem);
            }else{
                List<ComplianceDTO> listItem=new ArrayList<>();
                listItem.add(item);
                treeMap.put(item.getDueDateString(),listItem);
            }
        }


        List<ListItem> list=new ArrayList<>();
        for(String key:treeMap.keySet()){
            HeaderItem headerItem=new HeaderItem();
            headerItem.setHeaderItem(key);
            list.add(headerItem);
            for(ComplianceDTO item:treeMap.get(key)){
                EventItem eventItem=new EventItem();
                eventItem.setEventItem(item);
                list.add(eventItem);
            }
        }
        return list;
    }
}
