package prosayj.thinking.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import prosayj.thinking.mybatis.simple.base.ExecuteInterface;
import prosayj.thinking.mybatis.simple.base.MybatisEnvInit;
import prosayj.thinking.mybatis.simple.model.SysPrivilege;
import prosayj.thinking.mybatis.simple.model.SysRole;
import prosayj.thinking.mybatis.simple.plugin.PageRowBounds;
import prosayj.thinking.mybatis.simple.type.Enabled;

import static org.junit.jupiter.api.Assertions.*;


public class RoleMapperTest extends MybatisEnvInit {

    public RoleMapperTest() {
        super("mybatis-config.xml");
    }

    @Test
    public void testSelectById() {
        ((ExecuteInterface) () -> {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectById(1l);
            //role 不为空
            assertNotNull(role);
            //roleName = 管理员
            assertEquals("管理员", role.getRoleName());
        }).doExecuteSql(sqlSession,false);
    }


    @Test
    public void testSelectById2() {
        ((ExecuteInterface) () -> {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectById2(1l);
            //role 不为空
            assertNotNull(role);
            //roleName = 管理员
            assertEquals("管理员", role.getRoleName());
        }).doExecuteSql(sqlSession,false);
    }

    @Test
    public void testSelectAll() {
        ((ExecuteInterface) () -> {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectAll 方法查询所有角色
            List<SysRole> roleList = roleMapper.selectAll();
            //结果不为空
            assertNotNull(roleList);
            //角色数量大于 0 个
            assertTrue(roleList.size() > 0);
            //验证下划线字段是否映射成功
            assertNotNull(roleList.get(0).getRoleName());
        }).doExecuteSql(sqlSession,false);
    }

    @Test
    public void testSelectAllRoleAndPrivileges() {
        ((ExecuteInterface) () -> {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            for (SysRole role : roleList) {
                System.out.println("角色名：" + role.getRoleName());
                for (SysPrivilege privilege : role.getPrivilegeList()) {
                    System.out.println("权限名：" + privilege.getPrivilegeName());
                }
            }
        }).doExecuteSql(sqlSession,false);
    }

    @Test
    public void testSelectRoleByUserIdChoose() {
        ((ExecuteInterface) () -> {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //由于数据库数据 enable 都是 1，所以我们给其中一个角色的 enable 赋值为 0
            SysRole role = roleMapper.selectById(2L);
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
            //获取用户 1 的角色
            List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
            for (SysRole r : roleList) {
                System.out.println("角色名：" + r.getRoleName());
                if (r.getId().equals(1L)) {
                    //第一个角色存在权限信息
                    assertNotNull(r.getPrivilegeList());
                } else if (r.getId().equals(2L)) {
                    //第二个角色的权限为 null
                    assertNull(r.getPrivilegeList());
                    continue;
                }
                for (SysPrivilege privilege : r.getPrivilegeList()) {
                    System.out.println("权限名：" + privilege.getPrivilegeName());
                }
            }
        }).doExecuteSql(sqlSession,false);
    }

    @Test
    public void testUpdateById() {
        ((ExecuteInterface) () -> {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //由于数据库数据 enable 都是 1，所以我们给其中一个角色的 enable 赋值为 0
            SysRole role = roleMapper.selectById(2L);
            assertEquals(Enabled.enabled, role.getEnabled());
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
        }).doExecuteSql(sqlSession,false);
    }

    @Test
    public void testSelectAllByRowBounds() {
        ((ExecuteInterface) () -> {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //查询前两个，使用 RowBounds 类型不会查询总数
            RowBounds rowBounds = new RowBounds(0, 1);
            List<SysRole> list = roleMapper.selectAll(rowBounds);
            for (SysRole role : list) {
                System.out.println("角色名：" + role.getRoleName());
            }
            //使用 PageRowBounds 会查询总数
            PageRowBounds pageRowBounds = new PageRowBounds(0, 1);
            list = roleMapper.selectAll(pageRowBounds);
            //获取总数
            System.out.println("查询总数：" + pageRowBounds.getTotal());
            for (SysRole role : list) {
                System.out.println("角色名：" + role.getRoleName());
            }
            //再次查询
            pageRowBounds = new PageRowBounds(1, 1);
            list = roleMapper.selectAll(pageRowBounds);
            //获取总数
            System.out.println("查询总数：" + pageRowBounds.getTotal());
            for (SysRole role : list) {
                System.out.println("角色名：" + role.getRoleName());
            }

        }).doExecuteSql(sqlSession,false);
    }

}
