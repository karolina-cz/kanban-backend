package com.pw.kanban.application.room_member;

import com.pw.kanban.domain.room_member.Color;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberProductivityConverter {

    public String doubleArrayToString(Double[] input) {
        StringBuilder sb = new StringBuilder("");
        int i=0;
        for(Double value:input) {
            if(i!=0) {
                sb.append(",");
            }
            sb.append(value);
            i++;
        }
        return sb.toString();
    }

    public Double[] stringToDoubleArray(String input) {
        String[] stringArray = input.split(",");
        Double[] result = new Double[stringArray.length];
        for (int i = 0; i< stringArray.length; i++) {
            if (!stringArray[i].equals("null")){
                result[i] = Double.valueOf(stringArray[i]);
            } else {
                result[i] = null;
            }
        }
        return result;
    }
}
