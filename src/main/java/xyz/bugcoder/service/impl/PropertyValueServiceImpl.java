package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.Property;
import xyz.bugcoder.bean.PropertyValue;
import xyz.bugcoder.bean.PropertyValueExample;
import xyz.bugcoder.mapper.PropertyValueMapper;
import xyz.bugcoder.service.ProductService;
import xyz.bugcoder.service.PropertyService;
import xyz.bugcoder.service.PropertyValueService;

import java.util.List;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.service.impl
 * @Description:
 * @Date: 2019-09-22 09:38
 * @Author: Wyj
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;


    @Override
    public void update(PropertyValue pv) {

        propertyValueMapper.updateByPrimaryKey(pv);
    }

    @Override
    public void init(Product p) {

        // 现根据cid获取到所有属性
        List<Property> properties = propertyService.listByCid(p.getCid());
        for (Property pt : properties) {

            PropertyValue pv = get(p.getId(), pt.getId());
            pv.setProduct(p);
            pv.setProperty(pt);
            propertyValueMapper.insert(pv);
            System.out.println("111111111");
            System.out.println(pv);
            if (pv == null){

                PropertyValue propertyValue = new PropertyValue();
                propertyValue.setProduct(p);
                propertyValue.setProperty(pt);
                System.out.println("222222");
                System.out.println(propertyValue);
                propertyValueMapper.insert(propertyValue);
                System.out.println("3333333");
                System.out.println(propertyValue);
            }
        }
    }

    // 根据PID，ptid会获取到很多相同的属性值，只返回第一个
    @Override
    public PropertyValue get(int pid, int ptid) {

        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria()
                .andPidEqualTo(pid)
                .andPtidEqualTo(ptid);
        example.setOrderByClause("id");
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        return pvs.get(0);
    }

    // 根据PID获取到一个产品的所有属性值
    @Override
    public List<PropertyValue> list(int pid) {

        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria()
                .andPidEqualTo(pid);
        example.setOrderByClause("id");
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        return pvs;
    }
}
