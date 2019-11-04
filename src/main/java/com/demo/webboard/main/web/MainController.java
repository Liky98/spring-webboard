package com.demo.webboard.main.web;

import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.board.vo.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

//    private Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Resource
    private BoardService boardService;

    @GetMapping({"", "/"})
    public ModelAndView mainView(ModelAndView mav, HttpServletRequest request) throws Exception {
//        LOGGER.info("### MAIN PAGE");

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
        List<Board> list = boardService.selectBoardList();

        mav.addObject("list", list);
        mav.addObject("boardNo", boardNo);
        mav.setViewName("board/boardSideList");
        return mav;
    }
}
