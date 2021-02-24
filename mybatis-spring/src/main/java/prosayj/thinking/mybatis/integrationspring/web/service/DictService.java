package prosayj.thinking.mybatis.integrationspring.web.service;


import prosayj.thinking.mybatis.integrationspring.web.model.SysDict;

import java.util.List;

public interface DictService {

    SysDict findById(Long id);

    List<SysDict> findBySysDict(SysDict sysDict, Integer offset, Integer limit);

    boolean saveOrUpdate(SysDict sysDict);

    boolean deleteById(Long id);
}
