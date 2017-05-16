package com.dronamraju.svtemple.util;

import com.dronamraju.svtemple.model.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

public class ReadExcel {

    private static final String FILE_NAME = "/Users/admin/development/modules/svtco/onlineRegistration/src/main/java/com/dronamraju/svtemple/util/POSCustomers.xls";

    public static void main(String[] args) {
        User user = null;
        String USER = "svt0722712121140";
        String PASS = "SVTemple@2014";
        String dbUrl = "jdbc:sqlserver://svt0722712121140.db.2655960.hostedresource.com:1433;database=svt0722712121140";
        try {
            Connection conn = DriverManager.getConnection(dbUrl,USER,PASS);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int insertedCount = 0;
            int notInsertedCount = 0;
            StringBuffer sb = new StringBuffer();
            while (iterator.hasNext()) {
                try {
                    Row currentRow = iterator.next();
                    if (currentRow.getCell(27) != null && Util.isValidEmail(currentRow.getCell(27).toString())) {
                        String street1 = currentRow.getCell(5) == null ? "" : currentRow.getCell(5).toString();
                        String street2 = currentRow.getCell(6) == null ? "" : currentRow.getCell(6).toString();
                        user = new User(currentRow.getCell(2) == null ? "" : currentRow.getCell(2).toString(),
                                currentRow.getCell(1) == null ? "" : currentRow.getCell(1).toString(),
                                currentRow.getCell(27) == null ? "info@svtempleco.org" : currentRow.getCell(27).toString(),
                                currentRow.getCell(18) == null ? "" : currentRow.getCell(18).toString(),
                                street1 + street2,
                                currentRow.getCell(7) == null ? "" : currentRow.getCell(7).toString(),
                                currentRow.getCell(8) == null ? "" : currentRow.getCell(8).toString(),
                                currentRow.getCell(9) == null ? "" : currentRow.getCell(9).toString(),
                                AES.encrypt(Util.randomAlphaNumeric(5)),
                                currentRow.getCell(33) == null ? "" : currentRow.getCell(33).toString(),
                                currentRow.getCell(34) == null ? "" : currentRow.getCell(34).toString(),
                                currentRow.getCell(35) == null ? "" : currentRow.getCell(35).toString(),
                                currentRow.getCell(35) == null ? "" : currentRow.getCell(35).toString(),
                                currentRow.getCell(36) == null ? "" : currentRow.getCell(36).toString(),
                                currentRow.getCell(36) == null ? "" : currentRow.getCell(36).toString(),
                                currentRow.getCell(37) == null ? "" : currentRow.getCell(37).toString(),
                                currentRow.getCell(37) == null ? "" : currentRow.getCell(37).toString(),
                                currentRow.getCell(38) == null ? "" : currentRow.getCell(38).toString(),
                                currentRow.getCell(38) == null ? "" : currentRow.getCell(38).toString(),
                                currentRow.getCell(39) == null ? "" : currentRow.getCell(39).toString(),
                                currentRow.getCell(39) == null ? "" : currentRow.getCell(39).toString(),
                                false,
                                new Date(),
                                new Date(),
                                "System",
                                "System");
                        //System.out.println("user from POS: " + user);

                        String sqlQuery = "Insert into USER_TABLE (FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,STREET_ADDRESS,CITY,STATE,ZIP,PASSWORD," +
                                "FAMILY_GOTHRAM,PRIMARY_NAKSHATHRAM,PRIMARY_PADAM,SPOUSE_NAME,SPOUSE_NAKSHATHRAM,SPOUSE_PADAM,CHILD1_NAME,CHILD1_NAKSHATHRAM," +
                                "CHILD1_PADAM,CHILD2_NAME,CHILD2_NAKSHATHRAM,CHILD2_PADAM,CHILD3_NAME,CHILD3_NAKSHATHRAM,CHILD3_PADAM,CHILD4_NAME,CHILD4_NAKSHATHRAM," +
                                "CHILD4_PADAM,IS_ADMIN,UPDATED_DATE,CREATED_DATE,UPDATED_USER,CREATED_USER)\n" +
                                " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement preparedStmt = conn.prepareStatement(sqlQuery);
                        preparedStmt.setString(1, user.getFirstName());
                        preparedStmt.setString(2, user.getLastName());
                        preparedStmt.setString(3, user.getEmail());
                        preparedStmt.setString(4, user.getPhoneNumber());
                        preparedStmt.setString(5, user.getStreetAddress());
                        preparedStmt.setString(6, user.getCity());
                        preparedStmt.setString(7, user.getState());
                        preparedStmt.setString(8, user.getZip());
                        preparedStmt.setString(9, Util.randomAlphaNumeric(6));
                        preparedStmt.setString(10, user.getFamilyGothram());
                        preparedStmt.setString(11, user.getPrimaryNakshathram());
                        preparedStmt.setString(12, user.getPrimaryPadam());
                        preparedStmt.setString(13, user.getSpouseName());
                        preparedStmt.setString(14, user.getSpouseNakshathram());
                        preparedStmt.setString(15, user.getSpousePadam());
                        preparedStmt.setString(16, user.getChild1Name());
                        preparedStmt.setString(17, user.getChild1Nakshathram());
                        preparedStmt.setString(18, user.getChild1Padam());
                        preparedStmt.setString(19, user.getChild2Name());
                        preparedStmt.setString(20, user.getChild2Nakshathram());
                        preparedStmt.setString(21, user.getChild2Padam());
                        preparedStmt.setString(22, user.getChild3Name());
                        preparedStmt.setString(23, user.getChild3Nakshathram());
                        preparedStmt.setString(24, user.getChild3Padam());
                        preparedStmt.setString(25, user.getChild4Name());
                        preparedStmt.setString(26, user.getChild4Nakshathram());
                        preparedStmt.setString(27, user.getChild4Padam());
                        preparedStmt.setBoolean(28, false);
                        preparedStmt.setDate(29, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                        preparedStmt.setDate(30, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                        preparedStmt.setString(31, "System");
                        preparedStmt.setString(32, "System");
                        preparedStmt.execute();
                        insertedCount = insertedCount + 1;
                    } else {
                        notInsertedCount = notInsertedCount + 1;
                    }
                } catch (Exception e) {
                    //System.out.println("User not inserted: " + user.getFirstName() + ", " + user.getLastName() + ", " + user.getEmail() + "" + user.getPhoneNumber());
                    sb.append("'" + user.getEmail() + "'" + ",");
                    notInsertedCount = notInsertedCount + 1;
                    //System.out.println(e.getCause());
                    continue;
                }
            }
            System.out.println("Total rows inserted: " + insertedCount);
            System.out.println("Total rows not inserted: " + notInsertedCount);
            System.out.println("emails not inserted: " + sb.toString());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}