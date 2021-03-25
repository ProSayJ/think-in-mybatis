package demo.mybatis.dao;

import java.util.List;

import demo.mybatis.domain.Product;

/**
 * @author ProSayJ
 */
public interface ProductMapper {
    /**
     * 根据id查询商品信息
     *
     * @param id id
     * @return Product
     */
    Product find(long id);

    /**
     * 根据名称搜索商品信息
     *
     * @param name name
     * @return List<Product>
     */
    List<Product> findByName(String name);

    /**
     * 保存商品信息
     *
     * @param product product
     * @return long
     */
    long save(Product product);
}