package rml.dao;

import rml.model.UserWeapp;
import rml.request.UserRequest;

public interface MUserMapper {

   // int insert(User record);

    int updateUserWeapp(UserRequest userRequest);

    int insertWeapp(UserWeapp userWeapp);

    int selectUserWeapp(String openId);
}