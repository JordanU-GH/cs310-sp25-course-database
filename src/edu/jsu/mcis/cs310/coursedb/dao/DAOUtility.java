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
                // get number of columns in result set
                int numCols = meta.getColumnCount();
                // loop through all values in the result set (if the set is not empty), 
                // column by column, and add them to a jsonObject
                while(rs.next()){
                    JsonObject data = new JsonObject();
                    for(int i = 1; i <= numCols; i++){
                        // Add the column element into the JsonObject with the correct column name
                        String key = meta.getColumnName(i);
                        String value = rs.getString(i);
                        if(!rs.wasNull()){
                            data.put(key, value);
                        }
                    }
                    // add the jsonObject to records
                    records.add(data);
                }

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
