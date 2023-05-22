package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;
        try{
            conn = DB.getConeection();
            /*
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Carl Purple");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new Date(sdf.parse("22/05/2023").getTime()));
            st.setDouble(4, 3000);
            st.setInt(5, 4);
             */

            st = conn.prepareStatement(
                    "insert into department (Name) values ('D1'), ('D2'), ('D3'), ('D4')",
                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            }
//            System.out.println("Done! Rows affected: " + rowsAffected);
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
//        catch (ParseException e) {
//            throw new DbException(e.getMessage());
//        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
