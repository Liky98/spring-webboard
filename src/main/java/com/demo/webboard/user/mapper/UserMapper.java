package com.demo.webboard.user.mapper;

import com.demo.webboard.user.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<String> selectUserRoleList(String userNo);

    User selectUserMap(User user);

    int selectUserCount(User user);

    int updateUserInfo(User user);

}
