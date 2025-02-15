package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                // INSERT YOUR CODE HERE
                // get metadata
                ResultSetMetaData meta = rs.getMetaData();
                // move cursor to the first row in the result set
                rs.next();
                // get number of columns in result set
                int numCols = meta.getColumnCount();
                // loop through all values in the result set, column by column, and add them to a jsonObject
                while(!rs.isAfterLast()){
                    JsonObject data = new JsonObject();
                    for(int i = 1; i <= numCols; i++){
                        // Add the column element into the JsonObject with the correct column name
                        String key = meta.getColumnName(i);
                        data.put(key, rs.getString(i));
                    }
                    // add the jsonObject to records
                    records.add(data);
                    rs.next();
                }

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
