package com.demo.webboard.board.web;

import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.util.Paging;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Resource
    private BoardService boardService;

    /**
     * 게시물 조회 화면
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/{boardNo}")
    public ModelAndView boardView(@PathVariable("boardNo") long boardNo, Integer pageNo, ModelAndView mav) throws Exception {
        mav.addObject("board", boardService.selectBoardMap(boardNo));
        mav.addObject("pageNo", pageNo);
        mav.setViewName("board/boardView");
        return mav;
    }

    /**
     * 게시물 목록 조회
     * @param boardNo
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/list/{boardNo}")
    public ModelAndView postList(@PathVariable("boardNo") long boardNo, Integer pageNo, ModelAndView mav) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("boardNo", boardNo);

        // 페이징 처리
        paramsMap.put("totalCount", boardService.selectPostListCount(paramsMap));
        paramsMap.put("url", "/board/"+boardNo);
        paramsMap.put("pageNo", pageNo);
        Paging.makePaging(paramsMap);

        List<Map<String, Object>> list = boardService.selectPostList(paramsMap);

        mav.addObject("list", list);
        mav.addObject("paginate", paramsMap);
        mav.setViewName("board/postList");
        return mav;
    }

    /**
     * 게시물 등록 화면
     * @param boardNo
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/{boardNo}/post")
    public ModelAndView createPostView(@PathVariable("boardNo") long boardNo, ModelAndView mav) throws Exception {
        mav.addObject("board", boardService.selectBoardMap(boardNo));
        mav.setViewName("board/postCreateView");
        return mav;
    }

    /**
     * 게시물 등록
     * @param boardNo
     * @param paramsMap
     * @return
     * @throws Exception
     */
    @PostMapping("/{boardNo}/post")
    @ResponseBody
    public int createPost(@PathVariable("boardNo") long boardNo, @RequestBody Map<String, Object> paramsMap) throws Exception {

        return boardService.insertPostMap(paramsMap);
    }

    /**
     * 게시물 상세보기 화면
     * @param boardNo
     * @param postNo
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/{boardNo}/{postNo}")
    public ModelAndView readPostMap(@PathVariable("boardNo") long boardNo, @PathVariable("postNo") long postNo, ModelAndView mav) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("boardNo", boardNo);
        paramsMap.put("postNo", postNo);

        mav.addObject("board", boardService.selectBoardMap(boardNo));
        mav.addObject("post", boardService.selectPostMap(paramsMap));
        mav.setViewName("board/postReadView");
        return mav;
    }

    /**
     * 게시물 수정 화면
     * @param boardNo
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/{boardNo}/post/{postNo}")
    public ModelAndView updatePostView(@PathVariable("boardNo") long boardNo, @PathVariable("postNo") long postNo, ModelAndView mav) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("boardNo", boardNo);
        paramsMap.put("postNo", postNo);

        mav.addObject("board", boardService.selectBoardMap(boardNo));
        mav.addObject("post", boardService.selectPostMap(paramsMap));
        mav.setViewName("board/postUpdateView");
        return mav;
    }

    /**
     * 게시물 수정
     * @param boardNo
     * @param paramsMap
     * @return
     * @throws Exception
     */
    @PutMapping("/{boardNo}/post/{postNo}")
    @ResponseBody
    public int updatePostMap(@PathVariable("boardNo") long boardNo, @PathVariable("postNo") long postNo, @RequestBody Map<String, Object> paramsMap) throws Exception {
        return boardService.updatePostMap(paramsMap);
    }

    /**
     * 게시물 삭제
     * @param boardNo
     * @param postNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{boardNo}/post/{postNo}")
    @ResponseBody
    public int deletePostMap(@PathVariable("boardNo") long boardNo, @PathVariable("postNo") long postNo) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("boardNo", boardNo);
        paramsMap.put("postNo", postNo);

        return boardService.deletePostMap(paramsMap);
    }
}
