package com.demo.webboard.admin.web;

import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.board.vo.BoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<BoardVO> list = boardService.selectBoardList();

        mav.addObject("list", list);
        mav.setViewName("admin/boardReadView");
        return mav;
    }

    @PostMapping("/board")
    @ResponseBody
    public Map<String, Object> createBoardMap(@RequestBody @Valid BoardVO boardVO, BindingResult bindingResult) throws Exception {
        Map<String, Object> result = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> message = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> {
//                e.getCode();
                message.add(e.getDefaultMessage());
            });
            result.put("success", false);
            result.put("message", message.get(0));
        } else {
            try {
                String boardNo = boardService.insertBoardMap(boardVO);
                result.put("success", true);
                result.put("boardNo", boardNo);
            } catch (Exception e) {
                result.put("success", false);
                result.put("message", "등록 실패");
            }
        }

        return result;
    }

    @Deprecated
    @PutMapping("/board/{boardNo}")
    @ResponseBody
    public int updateBoardMap(@PathVariable("boardNo") long boardNo) throws Exception {
        return 0;
    }

    @DeleteMapping("/board/{boardNo}")
    @ResponseBody
    public int deleteBoardMap(@PathVariable("boardNo") long boardNo) throws Exception {
        try {
            return boardService.deleteBoardMap(boardNo);
        } catch (Exception e) {
            return -1;
        }
    }

}
