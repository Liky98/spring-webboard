package com.demo.webboard.board.vo;

import com.demo.webboard.util.Paging;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Board extends Paging {

    @NotEmpty
    private Long boardNo;

    @NotEmpty
    private String boardName;


    @NotEmpty
    private Long postNo;

    private String userId;
    private String nickname;
    private String title;
    private String content;
    private String createDt;
    private String updateDt;

}
