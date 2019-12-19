package com.demo.webboard.board.service.impl;

import com.demo.webboard.board.mapper.BoardDAO;
import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.board.vo.BoardVO;
import com.demo.webboard.util.CmmnAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
public class BoardServiceImpl extends CmmnAbstractServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public List<BoardVO> selectBoardList() throws Exception {
        return boardDAO.selectBoardList();
    }

    @Override
    public BoardVO selectBoardMap(long boardNo) throws Exception {
        return boardDAO.selectBoardMap(boardNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertBoardMap(BoardVO boardVO) {
        String result;
        boardDAO.insertBoardMap(boardVO);

        result = String.valueOf(boardVO.getBoardNo());
        if (null == result) {
            result = "1";
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBoardMap(long boardNo) {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardNo(boardNo);
        boardDAO.deletePostMap(boardVO);
        return boardDAO.deleteBoardMap(boardVO);
    }




    @Override
    public int selectPostListCount(BoardVO boardVO) throws Exception {
        return boardDAO.selectPostListCount(boardVO);
    }
    @Override
    public List<BoardVO> selectPostList(BoardVO boardVO) throws Exception {
        return boardDAO.selectPostList(boardVO);
    }

    @Override
    public int insertPostMap(BoardVO boardVO) {
        int result = 0;
        try {
            boardVO.setUserId(getUserId());
            boardVO.setNickname(getNickname());
            boardDAO.insertPostMap(boardVO);
            result = 1;
        } catch (Exception e) {
            result = -1;
        }

        return result;
    }

    @Override
    public BoardVO selectPostMap(BoardVO boardVO) throws Exception {
        return boardDAO.selectPostMap(boardVO);
    }

    @Override
    public int updatePostMap(BoardVO boardVO) throws Exception {
        return boardDAO.updatePostMap(boardVO);
    }

    @Override
    public int deletePostMap(BoardVO boardVO) throws Exception {
        return boardDAO.deletePostMap(boardVO);
    }
}
