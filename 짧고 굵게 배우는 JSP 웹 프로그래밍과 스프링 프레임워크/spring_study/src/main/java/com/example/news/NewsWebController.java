package com.example.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsWebController {
	final NewsDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 프로퍼티 파일로부터 저장 경로 참조
	@Value("${news.imgdir}")
	String fdir;

	@Autowired
	public NewsWebController(NewsDAO dao) {
		this.dao = dao;
	}

	@GetMapping("/list")
	public String listNews(Model m) {
		List<News> list;
		try {
			list = dao.getAll();
			m.addAttribute("newslist", list);
		} catch (Exception e) {
			e.printStackTrace();
//			ctx.log("뉴스 목록 생성 과정에서 문제가 발생했습니다!!");
//			req.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다.");
		}
		return "news/newsList"; // 뷰 이름을 반환합니다.
	}
}
