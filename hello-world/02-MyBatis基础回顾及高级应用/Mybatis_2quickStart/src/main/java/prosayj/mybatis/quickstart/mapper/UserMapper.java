package prosayj.mybatis.quickstart.mapper;

import prosayj.mybatis.quickstart.pojo.SimpleUser;

import java.io.IOException;
import java.util.List;

public interface UserMapper {

     List<SimpleUser> findAll() throws IOException;
     List<SimpleUser> findByIf(SimpleUser user) throws Exception;
     List<SimpleUser> findByIds(Integer[] ids) throws Exception;
     void insertUser(SimpleUser user) throws IOException;
     void deleteUser(Integer id) throws IOException;
     void updateUser(SimpleUser user) throws IOException;
}
