package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Property;
import xyz.bugcoder.bean.PropertyExample;
import xyz.bugcoder.mapper.PropertyMapper;
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
}
