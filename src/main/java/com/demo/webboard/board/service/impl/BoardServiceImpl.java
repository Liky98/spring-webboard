package com.demo.webboard.board.service.impl;

import com.demo.webboard.board.mapper.BoardMapper;
import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.util.CmmnAbstractServiceImpl;
import com.demo.webboard.util.DefaultTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BoardServiceImpl extends CmmnAbstractServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Map<String, Object>> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

    @Override
    public Map<String, Object> selectBoardMap(long boardNo) throws Exception {
        return boardMapper.selectBoardMap(boardNo);
    }

    @Override
    public String insertBoardMap(Map<String, Object> paramsMap) throws Exception {
        String result;
        try {
            boardMapper.insertBoardMap(paramsMap);

            result = paramsMap.get("boardNo").toString();
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
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("boardNo", boardNo);
            boardMapper.deletePostMap(paramsMap);
            result = boardMapper.deleteBoardMap(paramsMap);
        } catch (Exception e) {
            result = -1;
            tx.rollback();
        }
        tx.commit();
        tx.end();
        return result;
    }




    @Override
    public int selectPostListCount(Map<String, Object> paramsMap) throws Exception {
        return boardMapper.selectPostListCount(paramsMap);
    }
    @Override
    public List<Map<String, Object>> selectPostList(Map<String, Object> paramsMap) throws Exception {
        return boardMapper.selectPostList(paramsMap);
    }

    @Override
    public int insertPostMap(Map<String, Object> paramsMap) throws Exception {
        int result = 0;
        try {
            getLoginSessionMap(paramsMap);
            boardMapper.insertPostMap(paramsMap);
            result = 1;
        } catch (Exception e) {
            result = -1;
        }

        return result;
    }

    @Override
    public Map<String, Object> selectPostMap(Map<String, Object> paramsMap) throws Exception {
        return boardMapper.selectPostMap(paramsMap);
    }

    @Override
    public int updatePostMap(Map<String, Object> paramsMap) throws Exception {
        return boardMapper.updatePostMap(paramsMap);
    }

    @Override
    public int deletePostMap(Map<String, Object> paramsMap) throws Exception {
        return boardMapper.deletePostMap(paramsMap);
    }
}
