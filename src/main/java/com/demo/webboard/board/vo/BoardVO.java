package com.demo.webboard.board.vo;

import com.demo.webboard.util.Paging;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardVO extends Paging {

    private Long boardNo;

    @NotEmpty
    @Length(max=255)
    private String boardName;


    private Long postNo;

    private String userId;
    private String nickname;
    private String title;
    private String content;
    private String createDt;
    private String updateDt;

}
