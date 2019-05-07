package at.mueller.alfons.persistence;

import at.mueller.alfons.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryJDBC  implements IPatientRepository{
    private Connection connection;

    public PatientRepositoryJDBC() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc_test","root","");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new Exception("database driver not found",e);
        }catch (SQLException e){
            e.printStackTrace();
            throw new Exception("couldn't connect to database",e);
        }
    }

    @Override
    public void save(Patient patient) throws Exception {
        String stmt = "insert into patients (p_id, p_birthdate, p_name, p_country) values (?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(stmt);
        pstmt.setString(1,patient.getPatientID());
        pstmt.setString(3,patient.getName());
        //pstmt.setString(4,patient.getCountry());
        java.sql.Date d = new Date(patient.getBirthDate().getTime());
        pstmt.setDate(2,d);
        pstmt.executeUpdate();
    }

    @Override
    public List<Patient> allPatients() throws Exception {
        List<Patient> list = new ArrayList<>();
        String stmt = "select p_id, p_birthdate, p_name, p_country from patients";
        PreparedStatement pstmt = connection.prepareStatement(stmt);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Patient p = new Patient();
            p.setBirthDate(rs.getDate("p_birthdate"));
            p.setName(rs.getString("p_name"));
            p.setPatientID(rs.getString("p_id"));
            //p.setCountry(rs.getString("p_country"));
            list.add(p);
        }

        return list;
    }

}
