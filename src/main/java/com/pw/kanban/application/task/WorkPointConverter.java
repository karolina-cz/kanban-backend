package com.pw.kanban.application.task;

import com.pw.kanban.domain.room_member.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkPointConverter {
    public String colorArrayToString(List<Color> input) {
        StringBuilder sb = new StringBuilder("");
        int i=0;
        for(Color value:input) {
            if(i!=0) {
                sb.append(",");
            }
            sb.append(value);
            i++;
        }
        return sb.toString();
    }

    public String stringArrayToString(String[] input) {
        StringBuilder sb = new StringBuilder("");
        int i=0;
        for(String value:input) {
            if(i!=0) {
                sb.append(",");
            }
            sb.append(value);
            i++;
        }
        return sb.toString();
    }

    public List<Color> stringToColorArray(String input) {
        String[] stringArray = input.split(",");
        List<Color> colors = new ArrayList<>();
        for (String val : stringArray) {
            if (!val.equals("null")){
                colors.add(Color.valueOf(val));
            } else {
                colors.add(null);
            }
        }
        return colors;
    }


}
