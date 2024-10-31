package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	Connection conn = null;
	PreparedStatement pstmt;

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/C:/wonuSuhGram/database/jwbookdb";

	// 데이터베이스를 연결하는 메서드입니다.
	private void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 데이터베이스 연결을 끊는 메서드입니다.
	private void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 학생 한 명을 등록하는 메서드입니다.
	public void insert(Student s) {
		open();
		String sql = "INSERT INTO student (username, univ, birth, email, tel) VALUES(?, ?, ?, ?, ?);";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getUsername());
			pstmt.setString(2, s.getUniv());
			pstmt.setDate(3, s.getBirth());
			pstmt.setString(4, s.getEmail());
			pstmt.setString(5, s.getTel());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 전체 학생 목록을 가져오는 메서드 입니다.
	public List<Student> getAllStudents() {
		open();
		List<Student> allStudents = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement("SELECT * FROM student;");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setUsername(rs.getString("username"));
				s.setUniv(rs.getString("univ"));
				s.setBirth(rs.getDate("birth"));
				s.setEmail(rs.getString("email"));
				s.setTel(rs.getString("tel"));
				System.out.println(s.toString());

				allStudents.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return allStudents;
	}
}
