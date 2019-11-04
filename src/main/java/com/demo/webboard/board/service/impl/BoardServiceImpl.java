package com.demo.webboard.board.service.impl;

import com.demo.webboard.board.mapper.BoardMapper;
import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.board.vo.Board;
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
    public List<Board> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

    @Override
    public Board selectBoardMap(long boardNo) throws Exception {
        return boardMapper.selectBoardMap(boardNo);
    }

    @Override
    public String insertBoardMap(Board board) throws Exception {
        String result = "-1";
        try {
            boardMapper.insertBoardMap(board);

            if (null == board.getBoardNo()) {
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
            Board board = new Board();
            board.setBoardNo(boardNo);
            boardMapper.deletePostMap(board);
            result = boardMapper.deleteBoardMap(board);
        } catch (Exception e) {
            result = -1;
            tx.rollback();
        }
        tx.commit();
        tx.end();
        return result;
    }




    @Override
    public int selectPostListCount(Board board) throws Exception {
        return boardMapper.selectPostListCount(board);
    }
    @Override
    public List<Board> selectPostList(Board board) throws Exception {
        return boardMapper.selectPostList(board);
    }

    @Override
    public int insertPostMap(Board board) throws Exception {
        int result = 0;
        try {
            board.setUserId(getUserId());
            board.setNickname(getNickname());
            boardMapper.insertPostMap(board);
            result = 1;
        } catch (Exception e) {
            result = -1;
        }

        return result;
    }

    @Override
    public Board selectPostMap(Board board) throws Exception {
        return boardMapper.selectPostMap(board);
    }

    @Override
    public int updatePostMap(Board board) throws Exception {
        return boardMapper.updatePostMap(board);
    }

    @Override
    public int deletePostMap(Board board) throws Exception {
        return boardMapper.deletePostMap(board);
    }
}
