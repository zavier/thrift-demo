package com.zavier.thrift.demo;

import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService.Iface {

    @Override
    public UserSearchResult searchUsers(String name) throws TException {
        List<User> userList = new ArrayList<>();
        {
            final User user = new User();
            user.setName(name);
            userList.add(user);
        }
        {
            final User user = new User();
            user.setName(name + "1");
            userList.add(user);
        }

        final UserSearchResult result = new UserSearchResult();
        result.setUsers(userList);
        return result;
    }
}
