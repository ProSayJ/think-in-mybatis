package demo.mybatis.service;

import java.math.BigDecimal;
import java.util.List;

import demo.mybatis.DaoUtils;
import demo.mybatis.dao.ProductMapper;
import demo.mybatis.domain.Product;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * @author ProSayJ
 */
public class ProductService {

    // 创建商品
    public long createProduct(Product product) {
        // 检查product中的各个字段是否合法
        Preconditions.checkArgument(product != null, "product is null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(product.getName()), "product name is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(product.getDescription()), "description name is empty");
        Preconditions.checkArgument(product.getPrice().compareTo(new BigDecimal(0)) > 0,
                "price<=0 error");
        return DaoUtils.execute(sqlSession -> {
            // 通过ProductMapper中的save()方法完成持久化
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            return productMapper.save(product);
        });
    }

    public Product find(long productId) {
        // 检查productId参数是否合法
        Preconditions.checkArgument(productId > 0, "product id error");
        return DaoUtils.execute(sqlSession -> {
            // 通过ProductMapper中的find()方法精确查询Product
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            return productMapper.find(productId);
        });
    }

    public List<Product> find(String productName) {
        // 检查productName参数是否合法
        Preconditions.checkArgument(Strings.isNullOrEmpty(productName), "product id error");
        return DaoUtils.execute(sqlSession -> {
            // 根据productName模糊查询Product
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            return productMapper.findByName(productName);
        });
    }
}