package xyz.bugcoder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Category;
import xyz.bugcoder.bean.Property;
import xyz.bugcoder.bean.PropertyExample;
import xyz.bugcoder.mapper.PropertyMapper;
import xyz.bugcoder.service.CategoryService;
import xyz.bugcoder.service.PropertyService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019/9/16 16:47
 * @Author: Wyj
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    CategoryService categoryService;

    @Override
    public void add(Property p) {

        propertyMapper.insert(p);
    }

    @Override
    public void delete(int id) {

        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Property p) {

        propertyMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Property get(int id) {

        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Property> list() {

        PropertyExample example = new PropertyExample();
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }

    @Override
    public List<Property> listByCid(int cid) {

        PropertyExample example = new PropertyExample();
        example.createCriteria()
                .andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Property> pageList(int cid, int start, int size, int navigatePage) {

        Category c = categoryService.get(cid);
        List<Property> pts = listByCid(cid);
        PageHelper.offsetPage(start, size, "id desc");
        PageInfo<Property> page = new PageInfo<>(pts, navigatePage);

        return page;
    }
}
