package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                // set up Query and prepared statement
                String queryInsert = "INSERT INTO registration(studentid, termid, crn) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(queryInsert);
                // add values to prepared statement
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                // Execute query and determine if insertion was successful
                int updateCount = ps.executeUpdate();
                if (updateCount > 0){
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                // set up Query and prepared statement
                String queryDelete = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
                ps = conn.prepareStatement(queryDelete);
                // add values to prepared statement
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                // Set result to true if the query executes
                int updateCount = ps.executeUpdate();
                if(updateCount > 0){
                    result = true;
                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                // set up Query and prepared statement
                String queryDelete = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
                ps = conn.prepareStatement(queryDelete);
                // add values to prepared statement
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                // Set result to true if the query executes
                int updateCount = ps.executeUpdate();
                if(updateCount > 0){
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                String queryList = "SELECT * FROM registration WHERE studentid = ? AND termid = ?";
                // INSERT YOUR CODE HERE
                // set up prepared statement
                ps = conn.prepareStatement(queryList);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                // add values to prepared statement
                boolean hasresults = ps.execute();
                // if prepared statement executes, get the result set and convert it to Json for output
                if (hasresults) {
                    rs = ps.getResultSet();
                    result = DAOUtility.getResultSetAsJson(rs);
                }
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
