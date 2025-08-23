package com.example.news;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsApiController {
	final NewsDAO dao;

	// 프로퍼티 파일로부터 저장 경로 참조
	@Value("${news.imgdir}")
	String fdir;

	@Autowired
	public NewsApiController(NewsDAO dao) {
		this.dao = dao;
	}

	@GetMapping
	public List<News> getNewsList() {
		List<News> newsList = null;
		try {
			newsList = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
//			ctx.log("뉴스 목록 생성 과정에서 문제가 발생했습니다!!");
//			req.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다.");
		}
		return newsList; // 뷰 이름을 반환합니다.
	}

	/**
	 * 뉴스 등록을 처리하는 메서드입니다.
	 * 
	 * @param news 요청 본문에서 전달된 뉴스 데이터
	 * @return 등록 결과 메시지
	 */
	@PostMapping
	public String addNews(@RequestBody News news) {
		try {
			dao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			return "News API : 뉴스 등록 실패";
		}

		return "News API : 뉴스가 정상적으로 등록되었습니다.";
	}

	/**
	 * 특정 뉴스 기사를 제거하는 메서드입니다.
	 * 
	 * @param aid 뉴스 기사 ID
	 * @return 제거된 뉴스 기사
	 */
	@DeleteMapping("{aid}")
	public String delNews(@PathVariable("aid") int aid) {
		try {
			dao.delNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			return "News API : 뉴스 삭제 실패 - " + aid;
		}
		return "News API : 뉴스가 정상적으로 삭제되었습니다. - " + aid;
	}

	/**
	 * 특정 뉴스 기사를 조회하는 메서드입니다.
	 * 
	 * @param aid 뉴스 기사 ID
	 * @return 조회된 뉴스 기사
	 */
	@GetMapping("{aid}")
	public News getNews(@PathVariable("aid") int aid) {
		News news = null;

		try {
			news = dao.getNews(aid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return news;
	}
}
