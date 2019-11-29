package com.demo.webboard.user.mapper;

import com.demo.webboard.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<String> selectUserRoleList(String userNo);

    UserVO selectUserMap(UserVO userVO);

    int selectUserCount(UserVO userVO);

    int updateUserInfo(UserVO userVO);

}
