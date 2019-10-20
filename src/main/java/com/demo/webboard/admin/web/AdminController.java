package com.demo.webboard.admin.web;

import com.demo.webboard.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private BoardService boardService;

    /**
     * 관리자 메인페이지
     * @param mav
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping({"", "/"})
    public ModelAndView adminView(ModelAndView mav, HttpServletRequest request) throws Exception {

        mav.setViewName("admin/adminView");
        return mav;
    }

    /**
     * 게시판관리 화면
     * @param mav
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/board")
    public ModelAndView adminBoardView(ModelAndView mav, HttpServletRequest request) throws Exception {
        List<Map<String, Object>> list = boardService.selectBoardList();

        mav.addObject("list", list);
        mav.setViewName("admin/boardReadView");
        return mav;
    }

    @PostMapping("/board")
    @ResponseBody
    public String createBoardMap(@RequestBody Map<String, Object> paramsMap) throws Exception {
        return boardService.insertBoardMap(paramsMap);
    }

    @PutMapping("/board/{boardNo}")
    @ResponseBody
    public int updateBoardMap(@PathVariable("boardNo") long boardNo) throws Exception {
        return 0;
    }

    @DeleteMapping("/board/{boardNo}")
    @ResponseBody
    public int deleteBoardMap(@PathVariable("boardNo") long boardNo) throws Exception {
        return boardService.deleteBoardMap(boardNo);
    }

}
