package com.demo.webboard.board.vo;

import com.demo.webboard.util.Paging;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class BoardVO extends Paging {

    private Long boardNo;

    @NotBlank(message = "게시판명을 입력해주세요.")
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
