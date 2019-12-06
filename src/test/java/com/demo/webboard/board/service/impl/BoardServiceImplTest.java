package com.demo.webboard.board.service.impl;

import com.demo.webboard.board.service.BoardService;
import com.demo.webboard.board.vo.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void 테스트 () throws Exception {
        BoardVO vo = null;
        int count = 0;
        GIVEN:{
            vo = new BoardVO();
        }
        WHEN: {
            vo.setBoardNo(Long.parseLong("1"));
        }
        THEN: {
            count = boardService.selectPostListCount(vo);
            assertThat(count, is(2));
            assertEquals(count, 2);
            System.out.println(count);
        }
    }

}
