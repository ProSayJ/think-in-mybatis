package prosayj.mybatis.quickstart.dao;

import prosayj.mybatis.quickstart.pojo.SimpleUser;

import java.io.IOException;
import java.util.List;

public interface UserDao {
    List<SimpleUser> findAll() throws IOException;
    void insertUser(SimpleUser user) throws IOException;
    void deleteUser(Integer id) throws IOException;
    void updateUser(SimpleUser user) throws IOException;

}
