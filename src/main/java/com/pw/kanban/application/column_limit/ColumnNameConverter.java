package com.pw.kanban.application.column_limit;

import com.pw.kanban.domain.room_member.Color;
import com.pw.kanban.domain.task.ColumnName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColumnNameConverter {
    public String columnNameArrayToString(List<ColumnName> input) {
        StringBuilder sb = new StringBuilder("");
        int i=0;
        for(ColumnName value:input) {
            if(i!=0) {
                sb.append(",");
            }
            sb.append(value);
            i++;
        }
        return sb.toString();
    }

    public List<ColumnName> stringToColumnNameArray(String input) {
        String[] stringArray = input.split(",");
        List<ColumnName> columnNames = new ArrayList<>();
        for (String val : stringArray) {
            if (!val.equals("null")){
                columnNames.add(ColumnName.valueOf(val));
            } else {
                columnNames.add(null);
            }
        }
        return columnNames;
    }
}
