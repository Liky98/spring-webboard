package com.demo.webboard.board.mapper;

import com.demo.webboard.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    // 게시판목록 조회
    List<BoardVO> selectBoardList();

    // 게시판 정보 조회
    BoardVO selectBoardMap(long boardNo);

    // 게시판 등록
    int insertBoardMap(BoardVO boardVO);

    // 게시판 삭제
    int deleteBoardMap(BoardVO boardVO);



    // 게시물 전체 수 조회
    int selectPostListCount(BoardVO boardVO);

    // 게시물 목록 조회
    List<BoardVO> selectPostList(BoardVO boardVO);

    // 게시물 등록
    int insertPostMap(BoardVO boardVO);

    // 게시물 정보 조회
    BoardVO selectPostMap(BoardVO boardVO);

    // 게시물 수정
    int updatePostMap(BoardVO boardVO);

    // 게시물 삭제
    int deletePostMap(BoardVO boardVO);

}
