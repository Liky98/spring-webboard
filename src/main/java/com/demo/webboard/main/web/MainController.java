package com.demo.webboard.main.web;

import com.demo.webboard.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Resource
    private BoardService boardService;

    @GetMapping({"", "/"})
    public ModelAndView mainView(ModelAndView mav, HttpServletRequest request) throws Exception {
        mav.setViewName("main/mainView");

        return mav;
    }

    /**
     * 좌측 SideBar에 게시판 목록 조회
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/board/boardSideList")
    public ModelAndView boardSideList(ModelAndView mav, String boardNo) throws Exception {
        List<Map<String, Object>> list = boardService.selectBoardList();

        mav.addObject("list", list);
        mav.addObject("boardNo", boardNo);
        mav.setViewName("board/boardSideList");
        return mav;
    }
}
