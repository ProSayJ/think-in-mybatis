package prosayj.thinking.mybatis.simple.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import prosayj.thinking.mybatis.simple.support.ExecuteInterface;
import prosayj.thinking.mybatis.simple.support.MybatisEnvInit;
import prosayj.thinking.mybatis.simple.model.SysPrivilege;
import prosayj.thinking.mybatis.simple.model.SysRole;
import prosayj.thinking.mybatis.simple.model.SysUser;
import prosayj.thinking.mybatis.simple.type.Enabled;

import static org.junit.jupiter.api.Assertions.*;


public class A_UserMapperTest extends MybatisEnvInit {

    public A_UserMapperTest() {
        super("mybatis-config.xml");
    }


    @Test
    @DisplayName("00-简单插入-不返回主键")
    public void testInsert() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个 user 对象
            SysUser user = new SysUser();
            user.setUserName("test111");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片存到 byte 数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            //将新建的对象插入数据库中，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            assertEquals(1, userMapper.insert(user));
            logger.info("00-简单插入-不返回主键=====>{}", user);
            //id 为 null，我们没有给 id 赋值，并且没有配置回写 id 的值
            assertNull(user.getId());
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚.
        }).doExecuteSql(sqlSession, false);
    }

    @Test
    @DisplayName("01-简单插入-返回主键自增的值")
    public void testInsert2() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个 user 对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            //只插入 1 条数据
            assertEquals(1, userMapper.insert2(user));
            //因为 id 回写，所以 id 不为 null
            logger.info("01-简单插入-返回主键自增的值=====>{}", user);
            assertNotNull(user.getId());
        }).doExecuteSql(sqlSession, true);
    }

    @Test
    @DisplayName("02-简单插入-使用selectKey返回主键自增的值")
    public void testInsert3() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个 user 对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            //只插入 1 条数据
            int actual = userMapper.insert3(user);
            assertEquals(1, actual);
            //因为 id 回写，所以 id 不为 null
            logger.info("01-简单插入-使用selectKey返回主键自增的值=====>{}", actual);
            assertNotNull(user.getId());
        }).doExecuteSql(sqlSession, true);
    }


    @Test
    @DisplayName("03-update用法")
    public void testUpdateById() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询 1 个 user 对象
            SysUser user = userMapper.selectById(1L);
            //当前 userName 为 admin
            assertEquals("admin", user.getUserName());
            //修改用户名
            user.setUserName("admin_test");
            //修改邮箱
            user.setUserEmail("test@mybatis.tk");
            //更新数据，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.updateById(user);
            //只更新 1 条数据
            assertEquals(1, result);
            //根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字 admin_test
            assertEquals("admin_test", user.getUserName());
        }).doExecuteSql(sqlSession, true);
    }



    @Test
    @DisplayName("04-deleted用法")
    public void testDeleteById() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询 1 个 user 对象，根据 id = 1 查询
            SysUser user1 = userMapper.selectById(1L);
            //现在还能查询出 user 对象
            assertNotNull(user1);
            //调用方法删除
            assertEquals(1, userMapper.deleteById(1L));
            //再次查询，这时应该没有值，为 null
            assertNull(userMapper.selectById(1L));

            //使用 SysUser 参数再做一遍测试，根据 id = 1001  查询
            SysUser user2 = userMapper.selectById(1001L);
            //现在还能查询出 user 对象
            assertNotNull(user2);
            //调用方法删除，注意这里使用参数为 user2
            assertEquals(1, userMapper.deleteById(user2));
            //再次查询，这时应该没有值，为 null
            assertNull(userMapper.selectById(1001L));
            //使用 SysUser 参数再做一遍测试
        }).doExecuteSql(sqlSession, true);
    }

    @Test
    @DisplayName("05-多个接口参数的用法")
    public void testSelectRolesByUserIdAndRoleEnabled() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectRolesByUserIdAndRoleEnabled 方法查询用户的角色
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            //结果不为空
            assertNotNull(roleList);
            //角色数量大于 0 个
            assertTrue(roleList.size() > 0);
        }).doExecuteSql(sqlSession, false);
    }

    @Test
    @DisplayName("06-Mapper接口动态代理实现原理")
    public void testSelectAll() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectAll 方法查询所有用户
            List<SysUser> userList = userMapper.selectAll();
            //结果不为空
            assertNotNull(userList);
            //用户数量大于 0 个
            assertTrue(userList.size() > 0);
        }).doExecuteSql(sqlSession, false);
    }




    @Test
    @DisplayName("00-测试SelectById")
    public void testSelectById() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectById 方法，查询 id = 1 的用户
            SysUser user = userMapper.selectById(1l);
            //user 不为空
            assertNotNull(user);
            //userName = admin
            assertEquals("admin", user.getUserName());
        }).doExecuteSql(sqlSession, false);

    }




    @Test
    public void testSelectRolesByUserId() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectRolesByUserId 方法查询用户的角色
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            //结果不为空
            assertNotNull(roleList);
            //角色数量大于 0 个
            assertTrue(roleList.size() > 0);
        }).doExecuteSql(sqlSession, false);
    }



    @Test
    public void testSelectRolesByUserAndRole() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectRolesByUserIdAndRoleEnabled 方法查询用户的角色
            SysUser user = new SysUser();
            user.setId(1L);
            SysRole role = new SysRole();
            role.setEnabled(Enabled.enabled);
            List<SysRole> userList = userMapper.selectRolesByUserAndRole(user, role);
            //结果不为空
            assertNotNull(userList);
            //角色数量大于 0 个
            assertTrue(userList.size() > 0);
        }).doExecuteSql(sqlSession, false);
    }


    @Test
    public void testInsert2Selective() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个 user 对象
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setCreateTime(new Date());
            //插入数据库
            userMapper.insert2(user);
            //获取插入的这条数据
            user = userMapper.selectById(user.getId());
            assertEquals("test@mybatis.tk", user.getUserEmail());
        }).doExecuteSql(sqlSession, false);
        sqlSession.rollback();
        //不要忘记关闭 sqlSession
        sqlSession.close();

    }


    @Test
    public void testSelectByUser() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            assertTrue(userList.size() > 0);
            //只查询用户邮箱时
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            assertTrue(userList.size() > 0);
            //当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            //由于没有同时符合这两个条件的用户，查询结果数为 0
            assertTrue(userList.size() == 0);
        }).doExecuteSql(sqlSession, false);
    }

    @Test
    public void testSelectByIdOrUserName() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            assertNotNull(user);
            //当没有 id 时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            assertNotNull(user);
            //当 id 和 name 都为空时
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            assertNull(user);
        }).doExecuteSql(sqlSession, false);
    }

    @Test
    public void testUpdateByIdSelective() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询 1 个 user 对象
            SysUser user = new SysUser();
            //更新 id = 1 的用户
            user.setId(1L);
            //修改邮箱
            user.setUserEmail("test@mybatis.tk");
            //将新建的对象插入数据库中，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.updateByIdSelective(user);
            //只更新 1 条数据
            assertEquals(1, result);
            //根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字保持不变，但是邮箱变成了新的
            assertEquals("admin", user.getUserName());
            assertEquals("test@mybatis.tk", user.getUserEmail());
        }).doExecuteSql(sqlSession, false);
        //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
        sqlSession.rollback();
        //不要忘记关闭 sqlSession
        sqlSession.close();
    }

    @Test
    public void testSelectByIdList() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            //业务逻辑中必须校验 idList.size() > 0
            List<SysUser> userList = userMapper.selectByIdList(idList);
            assertEquals(2, userList.size());
        }).doExecuteSql(sqlSession, false);
    }

    @Test
    public void testInsertList() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个 user 对象
            List<SysUser> userList = new ArrayList<SysUser>();
            for (int i = 0; i < 2; i++) {
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }
            //将新建的对象批量插入数据库中，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.insertList(userList);
            assertEquals(2, result);
            for (SysUser user : userList) {
                System.out.println(user.getId());
            }
        }).doExecuteSql(sqlSession, false);
    }

    @Test
    public void testUpdateByMap() {
        ((ExecuteInterface) () -> {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询 1 个 user 对象
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 1L);
            map.put("user_email", "test@mybatis.tk");
            map.put("user_password", "12345678");
            //更新数据
            userMapper.updateByMap(map);
            //根据当前 id 查询修改后的数据
            SysUser user = userMapper.selectById(1L);
            assertEquals("test@mybatis.tk", user.getUserEmail());
        }).doExecuteSql(sqlSession, false);
    }

    @Test
    public void testSelectUserAndRoleById() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            //可以用 selectUserAndRoleById2 替换进行测试
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            //user 不为空
            assertNotNull(user);
            //user.role 也不为空
            assertNotNull(user.getRole());
        }).doExecuteSql(sqlSession, false);

    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            //user 不为空
            assertNotNull(user);
            //user.role 也不为空
            System.out.println("调用 user.equals(null)");
            user.equals(null);
            System.out.println("调用 user.getRole()");
            assertNotNull(user.getRole());
        }).doExecuteSql(sqlSession, false);

    }

    @Test
    public void testSelectAllUserAndRoles() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数：" + userList.size());
            for (SysUser user : userList) {
                System.out.println("用户名：" + user.getUserName());
                for (SysRole role : user.getRoleList()) {
                    System.out.println("角色名：" + role.getRoleName());
                    for (SysPrivilege privilege : role.getPrivilegeList()) {
                        System.out.println("权限名：" + privilege.getPrivilegeName());
                    }
                }
            }
        }).doExecuteSql(sqlSession, false);

    }

    @Test
    public void testSelectAllUserAndRolesSelect() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectAllUserAndRolesSelect(1L);
            System.out.println("用户名：" + user.getUserName());
            for (SysRole role : user.getRoleList()) {
                System.out.println("角色名：" + role.getRoleName());
                for (SysPrivilege privilege : role.getPrivilegeList()) {
                    System.out.println("权限名：" + privilege.getPrivilegeName());
                }
            }
        }).doExecuteSql(sqlSession, false);

    }

    @Test
    public void testSelectUserById() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            userMapper.selectUserById(user);
            assertNotNull(user.getUserName());
            System.out.println("用户名：" + user.getUserName());
        }).doExecuteSql(sqlSession, false);

    }

    @Test
    public void testSelectUserPage() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userName", "ad");
            params.put("offset", 0);
            params.put("limit", 10);
            List<SysUser> userList = userMapper.selectUserPage(params);
            Long total = (Long) params.get("total");
            System.out.println("总数:" + total);
            for (SysUser user : userList) {
                System.out.println("用户名：" + user.getUserName());
            }
        }).doExecuteSql(sqlSession, false);

    }

    @Test
    public void testInsertAndDelete() {
        ((ExecuteInterface) () -> {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片存到 byte 数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            //插入数据
            userMapper.insertUserAndRoles(user, "1,2");
            assertNotNull(user.getId());
            assertNotNull(user.getCreateTime());
            //可以执行下面的 commit 后查看数据库中的数据
            //sqlSession.commit();
            //测试删除刚刚插入的数据
            userMapper.deleteUserById(user.getId());
        }).doExecuteSql(sqlSession, false);
    }

}
