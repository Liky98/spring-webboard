package com.demo.webboard.board.service;

import com.demo.webboard.board.vo.BoardVO;

import java.util.List;

public interface BoardService {

    List<BoardVO> selectBoardList() throws Exception;
    BoardVO selectBoardMap(long boardNo) throws Exception;
    String insertBoardMap(BoardVO boardVO) throws Exception;
    int deleteBoardMap(long boardNo) throws Exception;

    int selectPostListCount(BoardVO boardVO) throws Exception;
    List<BoardVO> selectPostList(BoardVO boardVO) throws Exception;
    int insertPostMap(BoardVO boardVO) throws Exception;
    BoardVO selectPostMap(BoardVO boardVO) throws Exception;
    int updatePostMap(BoardVO boardVO) throws Exception;
    int deletePostMap(BoardVO boardVO) throws Exception;
}
