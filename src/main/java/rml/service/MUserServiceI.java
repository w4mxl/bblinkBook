package rml.service;

import rml.model.User;
import rml.model.UserWeapp;
import rml.request.UserRequest;

import java.util.List;

public interface MUserServiceI {

	List<User> getAll();
	
	User selectByPrimaryKey(String id);
	
    //int insert(User muser);

    User getUserMobile(String mobile);

	int insertWeapp(UserWeapp userWeapp);

	int updateUserWeapp (UserRequest userRequest);

}
