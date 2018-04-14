package cn.eakay.springboot.biz.impl;

import cn.eakay.springboot.biz.EakayService;
import cn.eakay.springboot.client.Eakay;
import cn.eakay.springboot.repository.mybaits.master.MasterEakayMapper;
import cn.eakay.springboot.repository.mybaits.slave.SlaveEakayMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by magic~ on 2018/4/12.
 */
@Service
public class EakayServiceImpl  implements EakayService
{

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MasterEakayMapper masterEakayMapper;
    @Autowired
    public SlaveEakayMapper slaveEakayMapper;


    @Override
    //多数据源的情况下声明事物需要指定具体名称
    @Transactional("masterTransactionManager")
    public List<Eakay> masterSelect(Eakay eakay)
    {
        //动态修改表名
        //eakay.setDynamicTableName("112222");
        return masterEakayMapper.select(eakay);
    }

    @Override
    @Transactional("slaveTransactionManager")
    public List<Eakay> slaveSelect(Eakay eakay)
    {
        return slaveEakayMapper.select(eakay);
    }

    /**
     * 分頁，注意注释中的写法会到导致bug 详情参见pagehelper官方文档
     * 查询全部结果，select(null)方法能达到同样的效果
     *PageHelper.startPage(1, 10);
     *List<Country> list;
     *if(param1 != null){
     *list = countryMapper.selectIf(param1);
     *} else {
     *list = new ArrayList<Country>();
     *}
     * @return
     */
    @Override
    public PageInfo<List<Eakay>> masterSelectAll()
    {
        PageHelper.startPage(1,10);
        List<Eakay> eakays= masterEakayMapper.selectAll();
        PageInfo pageInfo=new PageInfo(eakays);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Eakay>> slaveSelectAll()
    {
        PageHelper.startPage(1,10);
        List<Eakay> eakays= slaveEakayMapper.selectAll();
        PageInfo pageInfo=new PageInfo(eakays);
        return pageInfo;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     * @param eakay
     * @return
     */
    @Override
    public Eakay selectByPrimaryKey(Eakay eakay)
    {
        return masterEakayMapper.selectByPrimaryKey(eakay.getId());
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     * @param eakay
     * @return
     */
    @Override
    public Eakay selectOne(Eakay eakay)
    {
        return masterEakayMapper.selectOne(eakay);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     * @param eakay
     * @return
     */
    @Override
    public int selectCount(Eakay eakay)
    {
        return masterEakayMapper.selectCount(eakay);
    }

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     * @param eakay
     * @return
     */
    @Override
    public int insert(Eakay eakay)
    {
        return masterEakayMapper.insert(eakay);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param eakay
     * @return
     */
    @Override
    public int insertSelective(Eakay eakay)
    {
        return masterEakayMapper.insertSelective(eakay);
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     * @param eakay
     * @return
     */
    @Override
    public int updateByPrimaryKey(Eakay eakay)
    {
        return masterEakayMapper.updateByPrimaryKey(eakay);
    }

    /**
     * 根据主键更新属性不为null的值
     * @param eakay
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(Eakay eakay)
    {
        return masterEakayMapper.updateByPrimaryKeySelective(eakay);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * @param eakay
     * @return
     */
    @Override
    public int delete(Eakay eakay)
    {
        return masterEakayMapper.delete(eakay);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * @param eakay
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Eakay eakay)
    {
        return masterEakayMapper.delete(eakay);
    }

    /**
     * 说明：根据Example条件进行查询
     *  重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
     * @param eakay
     * @return
     */
    @Override
    public List<Eakay> selectByExample(Eakay eakay)
    {
         Example example = new Example(Eakay.class);
         example.createCriteria()
                .andCondition("title like '%title%' and id < 2");

       return   masterEakayMapper.selectByExample(example);
    }


    /**
     * 说明：根据Example条件进行查询总数
     * @param eakay
     * @return
     */
    @Override
    public int selectCountByExample(Eakay eakay)
    {
        Example example = new Example(Eakay.class);
        example.createCriteria()
                .andCondition("title like '%title%' and id < 2");

        return   masterEakayMapper.selectCountByExample(example);
    }

    /**
     * 根据Example条件更新实体record包含的全部属性，null值会被更新
     * @param eakay
     * @return
     */
    @Override
    public int updateByExample(Eakay eakay)
    {
        Example example = new Example(Eakay.class);
        example.createCriteria()
                .andCondition("title like '%title%' and id < 2");
        return masterEakayMapper.updateByExample(eakay,example);
    }

    /**
     * 根据Example条件更新实体record包含的不是null的属性值
     * @param eakay
     * @return
     */
    @Override
    public int updateByExampleSelective(Eakay eakay)
    {
        Example example = new Example(Eakay.class);
        example.createCriteria()
                .andCondition("title like '%title%' and id < 2");
        return masterEakayMapper.updateByExampleSelective(eakay,example);
    }

    /**
     * 根据Example条件删除数据
     * @param eakay
     * @return
     */
    @Override
    public int deleteByExample(Eakay eakay)
    {

        Example example = new Example(Eakay.class);
        example.createCriteria()
                .andCondition("title like '%title%' and id < 2");
        return masterEakayMapper.deleteByExample(example);
    }



}
