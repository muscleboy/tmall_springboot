package xyz.bugcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bugcoder.bean.Product;
import xyz.bugcoder.bean.Property;
import xyz.bugcoder.bean.PropertyValue;
import xyz.bugcoder.bean.PropertyValueExample;
import xyz.bugcoder.mapper.PropertyValueMapper;
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
        List<Property> pts = propertyService.listByCid(p.getCid());
        for (Property pt : pts) {
            PropertyValue pv = get(p.getId(), pt.getId());
            if (pv == null){

                pv = new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(pt.getId());
                propertyValueMapper.insert(pv);
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
        if (pvs.isEmpty())
            return null;
        return pvs.get(0);
    }

    // 根据PID获取到一个产品的所有属性值
    @Override
    public List<PropertyValue> list(int pid) {

        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria()
                .andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv : pvs) {

            Property pt = propertyService.get(pv.getPtid());
            // 给属性值设置对应的属性,
            pv.setProperty(pt);
        }
        return pvs;
    }
}
