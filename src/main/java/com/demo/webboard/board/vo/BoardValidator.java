package com.demo.webboard.board.vo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Deprecated
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BoardVO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BoardVO boardVO = (BoardVO)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "boardName", "required.boardName", "게시판명은 필수 입력사항입니다.");
        if (null == boardVO.getBoardName() || 255 < getLength(boardVO.getBoardName())) {
            errors.rejectValue("boardName", "lengthsize.boardName", "게시판명의 허용된 글자수가 초과되었습니다.");
        }
    }

    private int getLength(String value) {
        if (null == value)
            return 0;

        int b, i, c;
        for (b = i = 0; i < value.length(); i++) {
            c = value.charAt(i);
            b += ((c >> 11) > 0) ? 3 : (((c >> 7) > 0) ? 2 : 1);
        }
        return b;
    }
}
