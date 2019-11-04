package com.demo.webboard.board.mapper;

import com.demo.webboard.board.vo.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardMapper {

    // 게시판목록 조회
    List<Board> selectBoardList();

    // 게시판 정보 조회
    Board selectBoardMap(long boardNo);

    // 게시판 등록
    int insertBoardMap(Board board);

    // 게시판 삭제
    int deleteBoardMap(Board board);



    // 게시물 전체 수 조회
    int selectPostListCount(Board board);

    // 게시물 목록 조회
    List<Board> selectPostList(Board board);

    // 게시물 등록
    int insertPostMap(Board board);

    // 게시물 정보 조회
    Board selectPostMap(Board board);

    // 게시물 수정
    int updatePostMap(Board board);

    // 게시물 삭제
    int deletePostMap(Board board);

}
