package com.demo.webboard.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardMapper {

    // 게시판목록 조회
    List<Map<String, Object>> selectBoardList();

    // 게시판 정보 조회
    Map<String, Object> selectBoardMap(long boardNo);

    // 게시판 등록
    int insertBoardMap(Map<String, Object> paramsMap);

    // 게시판 삭제
    int deleteBoardMap(Map<String, Object> paramsMap);



    // 게시물 전체 수 조회
    int selectPostListCount(Map<String, Object> paramsMap);

    // 게시물 목록 조회
    List<Map<String, Object>> selectPostList(Map<String, Object> paramsMap);

    // 게시물 등록
    int insertPostMap(Map<String, Object> paramsMap);

    // 게시물 정보 조회
    Map<String, Object> selectPostMap(Map<String, Object> paramsMap);

    // 게시물 수정
    int updatePostMap(Map<String, Object> paramsMap);

    // 게시물 삭제
    int deletePostMap(Map<String, Object> paramsMap);

}
