package ch10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/C:/wonuSuhGram/database/jwbookdb";

	// DB 연결을 가져오는 메서드입니다. DBCP 을 사용하는 것이 좋습니다.
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 뉴스를 데이터베이스에 추가하는 메서드입니다.
	public void addNews(NewsVO n) throws SQLException {
		Connection conn = open();
		String sql = "INSERT INTO news (title, img, date, content) VALUES (?, ?, CURRENT_TIMESTAMP(), ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		// Java 9 이상부터 지원되는 try-with-resource 기법으로 예외 발생시 해당 리소스를 자동으로 close합니다.
		try (conn; pstmt;) {
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		}
	}

	// 모든 뉴스기사 목록을 가져오는 메서드입니다. 뉴스목록을 컨트롤러로 전달하기위해 List타입을 리턴합니다.
	protected List<NewsVO> getAll() throws SQLException {
		Connection conn = open();
		List<NewsVO> newsList = new ArrayList<>();
		String sql = "SELECT aid, title, PARSEDATETIME(date, 'yyyy-MM-dd hh:mm:ss') AS cdate FROM news;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs;) {
			while (rs.next()) {
				NewsVO n = new NewsVO();
				n.setAid(rs.getInt("id"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));

				newsList.add(n);
			}
		}
		return newsList;
	}
}
