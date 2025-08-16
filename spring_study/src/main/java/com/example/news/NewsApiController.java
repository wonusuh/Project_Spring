package com.example.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
}
