package com.demo.webboard.board.service.impl;

import com.demo.webboard.board.mapper.BoardMapper;
import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.board.vo.BoardVO;
import com.demo.webboard.util.CmmnAbstractServiceImpl;
import com.demo.webboard.util.DefaultTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class BoardServiceImpl extends CmmnAbstractServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardVO> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

    @Override
    public BoardVO selectBoardMap(long boardNo) throws Exception {
        return boardMapper.selectBoardMap(boardNo);
    }

    @Override
    public String insertBoardMap(BoardVO boardVO) throws Exception {
        String result;
        try {
            boardMapper.insertBoardMap(boardVO);

            result = String.valueOf(boardVO.getBoardNo());
            if (null == result) {
                result = "1";
            }
        } catch (Exception e) {
            result = null;
        }

        return result;
    }

    @Override
    public int deleteBoardMap(long boardNo) {
        DefaultTransactionManager tx = getTransactionManager(); // 트랜잭션 처리
        tx.start();

        int result;
        try {
            BoardVO boardVO = new BoardVO();
            boardVO.setBoardNo(boardNo);
            boardMapper.deletePostMap(boardVO);
            result = boardMapper.deleteBoardMap(boardVO);
        } catch (Exception e) {
            result = -1;
            tx.rollback();
        }
        tx.commit();
        tx.end();
        return result;
    }




    @Override
    public int selectPostListCount(BoardVO boardVO) throws Exception {
        return boardMapper.selectPostListCount(boardVO);
    }
    @Override
    public List<BoardVO> selectPostList(BoardVO boardVO) throws Exception {
        return boardMapper.selectPostList(boardVO);
    }

    @Override
    public int insertPostMap(BoardVO boardVO) throws Exception {
        int result = 0;
        try {
            boardVO.setUserId(getUserId());
            boardVO.setNickname(getNickname());
            boardMapper.insertPostMap(boardVO);
            result = 1;
        } catch (Exception e) {
            result = -1;
        }

        return result;
    }

    @Override
    public BoardVO selectPostMap(BoardVO boardVO) throws Exception {
        return boardMapper.selectPostMap(boardVO);
    }

    @Override
    public int updatePostMap(BoardVO boardVO) throws Exception {
        return boardMapper.updatePostMap(boardVO);
    }

    @Override
    public int deletePostMap(BoardVO boardVO) throws Exception {
        return boardMapper.deletePostMap(boardVO);
    }
}
