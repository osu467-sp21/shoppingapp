/**
 * Class: EligibleZips()
 * Description: Attempts to retrieve all zip codes within a certain distance
 *              of a user provided zip code. Zip code distances are determined
 *              using the haversine formula that is executed as a stored
 *              procedure in the database schema. The haversine formula calculates
 *              zip code distances based on the direct path between two points
 *              and not the distance it would take to drive from one point to
 *              another, for example. All zip code information, such as latitude and
 *              longitude, is stored in the database.
 *
 * Usage:
 *      EligibleZips ez = new EligibleZips();
 *      ez.determineEligibleZips(90210, 100);
 *      if (ez.isErrorFlagSet()) {
 *          System.out.println(ez.getErrorCode());
 *          System.out.println(ez.getErrorMsg());
 *          return;
 *      }
 *      Set<Integer> setAllZips = ez.getSetAllEligibleZips();
 *      String strAllZips = ez.getStringAllEligibleZips();
 *
 * Error codes:
 *      1001: Error sending target zip lat/lon query
 *      1002: Error parsing target zip lat/lon query result set
 *      1003: Error sending eligible zip query
 *      1004: Error parsing eligible zip query result set
 */
package com.shoppingapp.shoppingapp.ShoppingList;

import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

@Service
public class EligibleZips {
    private boolean errorFlag = false;
    private double lat = -1400;
    private double lon = -1400;
    private int distance = -100;
    private int zipCode = -100;
    private int errorCode = -999;
    private String errorMsg = "";
    private final Set<Integer> zipSet = new HashSet<>();

    public boolean isErrorFlagSet() { return this.errorFlag; }

    public Integer getErrorCode() { return this.errorCode; }

    public String getErrorMsg() { return this.errorMsg; }

    public Set<Integer> getSetAllEligibleZips() { return this.zipSet; }

    /**
     * Get all eligible zips in string format.
     *
     * @return string with format "11111,22222,33333"
     */
    public String getStringAllEligibleZips() {
        String s = "";

        for (Integer i : this.zipSet) {
            s = s + i + ",";
        }

        return s.substring(0, s.length() - 1);
    }

    /**
     * Searches for all zip codes within a certain distance from a
     * user provided target zip code.
     *
     * @param zip integer representing the target zip code
     * @param dist integer representing the distance to search
     *             for eligible zip codes from target zip
     */
    public void determineEligibleZips(int zip, int dist) {
        this.zipCode = zip;
        this.distance = dist;

        sendTargetZipCoordsQuery();
        if (!isErrorFlagSet()) { sendEligibleZipsQuery(); }
    }

    private void setErrorFlag() {
        this.errorFlag = true;
    }

    private void setErrorCode(int i) { this.errorCode = i; }

    private void setErrorMsg(String s) { this.errorMsg = s; }

    /**
     * @return query for getting latitude and longitude for target zip
     */
    private String getZipSelectQuery() {
        return "SELECT lat, lon FROM zip_codes WHERE zip = " + this.zipCode;
    }

    /**
     * @return query for getting all eligible zips within certain distance of target zip
     */
    private String getEligibleZipsQuery() {
        return "{call sp_GetEligibleZips(?, ?, ?)}";
    }

    private Vector<String> getDatabaseInfo() {
        Vector<String> v = new Vector<>();
        v.add(""); //url
        v.add(""); //username
        v.add(""); //password
        return v;
    }

    /**
     * Performs a database query that attempts to find latitude and longitude for
     * a target zip code.
     */
    private void sendTargetZipCoordsQuery() {
        Vector<String> dbInfo = getDatabaseInfo();
        String query = getZipSelectQuery();

        try {
            Connection conn = DriverManager.getConnection(
                    dbInfo.get(0),
                    dbInfo.get(1),
                    dbInfo.get(2)
            );
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            parseTargetZipCoordsQueryResponse(rs);
            conn.close();
        } catch(Exception e){
            setErrorFlag();
            setErrorCode(1001);
            setErrorMsg(e.getMessage());
        }
    }

    /**
     * Parses the ResultSet from the lat/lon target zip code query. The query
     * will return lat and lon in that order. Both values are stored as doubles
     * in the database.
     *
     * Stores the lat and lon values in the instance variables:
     *      this.lat
     *      this.lon
     *
     * @param rs ResultSet from the lat/lon target zip code query
     */
    private void parseTargetZipCoordsQueryResponse(ResultSet rs) {
        try {
            rs.next();
            this.lat = rs.getDouble(1);
            this.lon = rs.getDouble(2);
        } catch(Exception e) {
            setErrorFlag();
            setErrorCode(1002);
            setErrorMsg(e.getMessage());
        }
    }

    /**
     * Performs a database query that attempts to find all eligible zip codes
     * that are within a certain distance from a target zip code.
     */
    private void sendEligibleZipsQuery() {
        Vector<String> dbInfo = getDatabaseInfo();
        String query = getEligibleZipsQuery();

        try {
            Connection conn = DriverManager.getConnection(
                    dbInfo.get(0),
                    dbInfo.get(1),
                    dbInfo.get(2)
            );
            CallableStatement st = conn.prepareCall(query);
            st.setDouble(1, this.lat);
            st.setDouble(2, this.lon);
            st.setInt(3, this.distance);
            ResultSet rs = st.executeQuery();
            parseEligibleZipsQueryResponse(rs);
            st.close();
            conn.close();
        }catch(Exception e){
            setErrorFlag();
            setErrorCode(1003);
            setErrorMsg(e.getMessage());
        }
    }

    /**
     * Parses the ResultSet from the get all eligile zip codes query.
     * The query will return a table with zip code and distance from target,
     * in that order. Zip is stored as integer in database while distance is
     * stored as double.
     *
     * Stores all zip codes in the hash set instance variable:
     *      this.zipSet
     *
     * As of 19Apr2021, the distance each zip code is from the target
     * is not being used.
     *
     * @param rs ResultSet from the lat/lon target zip code query
     */
    private void parseEligibleZipsQueryResponse(ResultSet rs) {
        try {
            while(rs.next()) {
                this.zipSet.add(rs.getInt(1));
            }
        } catch(Exception e) {
            setErrorFlag();
            setErrorCode(1004);
            setErrorMsg(e.getMessage());
        }
    }
}
