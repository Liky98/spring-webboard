package com.demo.webboard.board.service;

import com.demo.webboard.board.vo.Board;

import java.util.List;

public interface BoardService {

    List<Board> selectBoardList() throws Exception;
    Board selectBoardMap(long boardNo) throws Exception;
    String insertBoardMap(Board board) throws Exception;
    int deleteBoardMap(long boardNo) throws Exception;

    int selectPostListCount(Board board) throws Exception;
    List<Board> selectPostList(Board board) throws Exception;
    int insertPostMap(Board board) throws Exception;
    Board selectPostMap(Board board) throws Exception;
    int updatePostMap(Board board) throws Exception;
    int deletePostMap(Board board) throws Exception;
}
