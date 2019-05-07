package at.mueller.alfons.persistence;

import at.mueller.alfons.Study;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudyRepositoryJDBC implements IStudyRepository{
    private Connection connection;

    public StudyRepositoryJDBC() throws Exception {
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
    public void save(Study study) throws Exception {
        String stmt = "insert into studies (s_id, s_date, s_description) values (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(stmt);
        pstmt.setString(1,study.getStudyInstanceUID());
        pstmt.setString(3,study.getStudyDescription());
        java.sql.Date d = new Date(study.getStudyDate().getTime());
        pstmt.setDate(2,d);
        pstmt.executeUpdate();
    }

    @Override
    public List<Study> allStudies() throws Exception {
        List<Study> list = new ArrayList<>();
        String stmt = "select s_id, s_date, s_description from studies";
        PreparedStatement pstmt = connection.prepareStatement(stmt);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
         Study s = new Study();
         s.setStudyDate(rs.getDate("s_date"));
         s.setStudyDescription(rs.getString("s_description"));
         s.setStudyInstanceUID(rs.getString("s_id"));
         list.add(s);
        }

        return list;
    }
}
