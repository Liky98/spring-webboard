package com.demo.webboard.board.web;

import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.board.vo.BoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
        mav.addObject("boardVO", boardService.selectBoardMap(boardNo));
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
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNo(boardNo);

        // 페이징 처리
        boardVO.setTotalCount(boardService.selectPostListCount(boardVO));
        boardVO.setUrl("/board/"+boardNo);
        boardVO.setPageNo(pageNo);
        boardVO.makePaging();

        List<BoardVO> list = boardService.selectPostList(boardVO);

        mav.addObject("list", list);
        mav.addObject("paginate", boardVO);
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
        mav.addObject("boardVO", boardService.selectBoardMap(boardNo));
        mav.setViewName("board/postCreateView");
        return mav;
    }

    /**
     * 게시물 등록
     *
     * @param boardVO
     * @return
     * @throws Exception
     */
    @PostMapping("/{boardNo}/post")
    @ResponseBody
    public int createPost(@RequestBody BoardVO boardVO) throws Exception {
        try {
            boardService.insertPostMap(boardVO);
            return 1;
        } catch (Exception e) {
            return -1;
        }
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
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNo(boardNo);
        boardVO.setPostNo(postNo);

        mav.addObject("boardVO", boardService.selectBoardMap(boardNo));
        mav.addObject("postVO", boardService.selectPostMap(boardVO));
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
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNo(boardNo);
        boardVO.setPostNo(postNo);

        mav.addObject("boardVO", boardService.selectBoardMap(boardNo));
        mav.addObject("postVO", boardService.selectPostMap(boardVO));
        mav.setViewName("board/postUpdateView");
        return mav;
    }

    /**
     * 게시물 수정
     * @param boardNo
     * @param postNo
     * @param boardVO
     * @return
     * @throws Exception
     */
    @PutMapping("/{boardNo}/post/{postNo}")
    @ResponseBody
    public int updatePostMap(@PathVariable("boardNo") long boardNo, @PathVariable("postNo") long postNo, @RequestBody BoardVO boardVO) throws Exception {
        return boardService.updatePostMap(boardVO);
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
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNo(boardNo);
        boardVO.setPostNo(postNo);

        return boardService.deletePostMap(boardVO);
    }
}
