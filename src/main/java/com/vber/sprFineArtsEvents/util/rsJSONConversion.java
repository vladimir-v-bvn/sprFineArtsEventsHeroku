package com.vber.sprFineArtsEvents.util;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class rsJSONConversion {

  public static String convertResultSetToJSONObject(ResultSet resultSet) throws SQLException {
    JSONObject JSONObject = new JSONObject();
    while (resultSet.next()) {
      for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
        JSONObject.put(resultSet.getMetaData().getColumnLabel(i + 1), resultSet.getObject(i + 1));
      }
    }
    return JSONObject.toString();
  }

  public static String convertResultSetToJSONArray(ResultSet resultSet) throws SQLException {
    JSONArray jsonArray = new JSONArray();
    while (resultSet.next()) {
      JSONObject obj = new JSONObject();
      for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
        obj.put(resultSet.getMetaData().getColumnLabel(i + 1), resultSet.getObject(i + 1));
      }
      jsonArray.put(obj);
    }
    return jsonArray.toString();
  }

  public static String convertIntegerToJSONObject(String s, Integer i) {
    JSONObject JSONObject = new JSONObject();
    JSONObject.put(s, i);
    return JSONObject.toString();
  }

}
