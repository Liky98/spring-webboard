package com.demo.webboard.board.mapper;

import com.demo.webboard.board.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO {
    private static final String NAMESPACE = "com.demo.webboard.board.mapper.BoardMapper.";

    @Autowired
    private SqlSession sqlSession;



    // 게시판목록 조회
    public List<BoardVO> selectBoardList() {
        return sqlSession.selectList(NAMESPACE + "selectBoardList");
    }

    // 게시판 정보 조회
    public BoardVO selectBoardMap(long boardNo) {
        return sqlSession.selectOne(NAMESPACE + "selectBoardMap", boardNo);
    }

    // 게시판 등록
    public int insertBoardMap(BoardVO boardVO) {
        return sqlSession.insert(NAMESPACE + "insertBoardMap", boardVO);
    }

    // 게시판 삭제
    public int deleteBoardMap(BoardVO boardVO) {
        return sqlSession.delete(NAMESPACE + "deleteBoardMap", boardVO);
    }



    // 게시물 전체 수 조회
    public int selectPostListCount(BoardVO boardVO) {
        return sqlSession.selectOne(NAMESPACE + "selectPostListCount", boardVO);
    }

    // 게시물 목록 조회
    public List<BoardVO> selectPostList(BoardVO boardVO) {
        return sqlSession.selectList(NAMESPACE + "selectPostList", boardVO);
    }

    // 게시물 등록
    public int insertPostMap(BoardVO boardVO) {
        return sqlSession.insert(NAMESPACE + "insertPostMap", boardVO);
    }

    // 게시물 정보 조회
    public BoardVO selectPostMap(BoardVO boardVO) {
        return sqlSession.selectOne(NAMESPACE + "selectPostMap", boardVO);
    }

    // 게시물 수정
    public int updatePostMap(BoardVO boardVO) {
        return sqlSession.update(NAMESPACE + "updatePostMap", boardVO);
    }

    // 게시물 삭제
    public int deletePostMap(BoardVO boardVO) {
        return sqlSession.delete(NAMESPACE + "deletePostMap", boardVO);
    }
}
