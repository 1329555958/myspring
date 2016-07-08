
	namespace java com.sohu.thrift.generator.test.thrift

	enum Status {
			NORMAL = 0,
			BLOCKED = 1
	}

	struct Account {
			1:i32 id,
			2:string name
	}
	struct User {
			1:i32 id,
			2:string name,
			3:bool sex,
			4:Status status,
			5:list<i64> ids,
			6:Account account
	}

	service ICommonUserService {
		 	map<i64, list<i64>> getGroupUsers(1:list<string> arg0, 2:list<User> arg1, 3:list<i64> arg2, 4:i64 arg3),
		 	list<string> testCase1(1:map<i32, string> arg0, 2:list<User> arg1, 3:list<string> arg2, 4:i64 arg3, 5:string arg4),
		 	map<string, list<User>> getUsersByName(1:list<string> arg0),
		 	User login(1:i32 arg0, 2:string arg1),
		 	map<i64, User> getUserByIds(1:list<User> arg0),
		 	list<User> getUserIds(1:i64 arg0),
		 	bool saveUser(1:User arg0),
		 	User getUserById(1:i64 arg0)
	}