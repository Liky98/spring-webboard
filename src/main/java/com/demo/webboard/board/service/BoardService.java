package com.demo.webboard.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

    List<Map<String, Object>> selectBoardList() throws Exception;
    Map<String, Object> selectBoardMap(long boardNo) throws Exception;
    String insertBoardMap(Map<String, Object> paramsMap) throws Exception;
    int deleteBoardMap(long boardNo) throws Exception;

    int selectPostListCount(Map<String, Object> paramsMap) throws Exception;
    List<Map<String, Object>> selectPostList(Map<String, Object> paramsMap) throws Exception;
    int insertPostMap(Map<String, Object> paramsMap) throws Exception;
    Map<String, Object> selectPostMap(Map<String, Object> paramsMap) throws Exception;
    int updatePostMap(Map<String, Object> paramsMap) throws Exception;
    int deletePostMap(Map<String, Object> paramsMap) throws Exception;
}
