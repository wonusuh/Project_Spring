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
	private Connection open() {
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
				n.setAid(rs.getInt("aid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("cdate"));

				newsList.add(n);
			}
		}
		return newsList;
	}

	// 뉴스목록에서 뉴스기사 한개를 클릭했을때 해당 기사의 세부내용 화면을 보여주기위한 메서드입니다.
	protected NewsVO getNews(int aid) throws SQLException {
		Connection conn = open();

		NewsVO n = new NewsVO();
		String sql = "SELECT aid, title, img, PARSEDATETIME(date, 'yyyy-MM-dd hh:mm:ss') as cdate, content FROM news WHERE aid=?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();

		rs.next();

		try (conn; pstmt; rs;) {
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			pstmt.executeQuery();
			return n;
		}
	}

	// 선택한 뉴스를 삭제하는 메서드입니다.
	protected void delNews(int aid) throws SQLException {
		Connection conn = this.open();

		String sql = "DELETE FROM news WHERE aid = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt;) {
			pstmt.setInt(1, aid);
			// 삭제된 뉴스 기사가 없을 경우 에러를 발생시킵니다.
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("DB 에러");
			}
		}
	}
}
