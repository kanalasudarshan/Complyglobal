package app.complyglobal.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import app.complyglobal.dto.ComplianceDTO;

/**
 * Created by SUDARSHAN REDDY on 29-10-2017.
 */

public class FileJsonReader {
    public static List<ComplianceDTO> getJsonData(Context context){
        List<ComplianceDTO> listItem = null;
        try {
            InputStream is = context.getAssets().open("compliance.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            listItem=settingToDTO(json);
            Log.i("FileJsonReader",listItem.toString());
        } catch (IOException ex) {
            Log.e("FileJsonReader","",ex);
        }
        return listItem;
    }

    private static List<ComplianceDTO> settingToDTO(String json){
        List<ComplianceDTO> listItem=new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonList=jsonArray.getJSONObject(i);

                ComplianceDTO dto=new ComplianceDTO();
                dto.setJectdId(Integer.parseInt(jsonList.getString("jectdId")));
                dto.setComplianceTaskname(jsonList.getString("complianceTaskName"));
                dto.setComplianceTypeName(jsonList.getString("areaName"));
                dto.setComplianceCaetgoryName(jsonList.getString("parentComplianceName"));
                dto.setComplianceSubCategoryName(jsonList.getString("subComplianceName"));
                dto.setStateName(jsonList.getString("steteName"));
                dto.setDueDateString(jsonList.getString("dueDate"));
                dto.setEntityName(jsonList.getString("entityName"));
                dto.setStatus(jsonList.getString("status"));
                if(jsonList.getString("isOverDueTask")!=null)
                   dto.setOverDue(Boolean.parseBoolean(jsonList.getString("isOverDueTask")));
                dto.setWorkFlow(Boolean.parseBoolean(jsonList.getString("isWorkflowTask")));

                dto.setDueDate(new Timestamp(Constants.dateFormat.parse(jsonList.getString("dueDate")).getTime()));

                listItem.add(dto);
            }
        } catch (Exception ex) {
            Log.e("FileJsonReader","",ex);
        }

        return listItem;
    }
}
