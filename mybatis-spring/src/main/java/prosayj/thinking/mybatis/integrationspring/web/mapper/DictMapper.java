package prosayj.thinking.mybatis.integrationspring.web.mapper;

import org.apache.ibatis.session.RowBounds;
import prosayj.thinking.mybatis.integrationspring.web.model.SysDict;

import java.util.List;

public interface DictMapper {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    SysDict selectByPrimaryKey(Long id);

    /**
     * 条件查询
     *
     * @param sysDict
     * @return
     */
    List<SysDict> selectBySysDict(SysDict sysDict, RowBounds rowBounds);

    /**
     * 新增
     *
     * @param sysDict
     * @return
     */
    int insert(SysDict sysDict);

    /**
     * 根据主键更新
     *
     * @param sysDict
     * @return
     */
    int updateById(SysDict sysDict);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
}
