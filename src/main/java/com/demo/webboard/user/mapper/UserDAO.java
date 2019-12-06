package com.demo.webboard.user.mapper;

import com.demo.webboard.user.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private static final String NAMESPACE = "com.demo.webboard.user.mapper.UserMapper.";

    @Autowired
    @Qualifier(value = "sqlSession-h2")
    private SqlSession sqlSession;


    public List<String> selectUserRoleList(String userNo) {
        return sqlSession.selectList(NAMESPACE + "selectUserRoleList", userNo);
    }

    public UserVO selectUserMap(UserVO userVO) {
        return sqlSession.selectOne(NAMESPACE + "selectUserMap", userVO);
    }

    public int selectUserCount(UserVO userVO) {
        return sqlSession.selectOne(NAMESPACE + "selectUserCount", userVO);
    }

    public int updateUserInfo(UserVO userVO) {
        return sqlSession.update(NAMESPACE + "updateUserInfo", userVO);
    }

}
