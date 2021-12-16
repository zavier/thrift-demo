struct User {
    1:string name;
    2:i32 age;
}

struct UserSearchResult {
    1:list<User> users;
}

service UserService {
    UserSearchResult searchUsers(1:string name);
}
