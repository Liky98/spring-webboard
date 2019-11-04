package com.demo.webboard.board.vo;

import com.demo.webboard.util.Paging;
import lombok.Data;

@Data
public class Board extends Paging {

    private Long boardNo;
    private String boardName;

    private Long postNo;
    private String userId;
    private String nickname;
    private String title;
    private String content;
    private String createDt;
    private String updateDt;

}
