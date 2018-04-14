package cn.eakay.springboot.test.mybaits;

import cn.eakay.springboot.biz.EakayService;
import cn.eakay.springboot.client.Eakay;
import cn.eakay.springboot.test.BaseTest;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by magic~ on 2018/4/10.
 */
public class MybaitsTest extends BaseTest
{
    private static final Logger logger = LoggerFactory.getLogger(MybaitsTest.class);

    @Autowired
    public EakayService  eakayService;

    @Test
    public void testEakayMapper()
    {
        Eakay eakay=new Eakay();
        eakay.setId(1);
        List<Eakay>  eakays=eakayService.masterSelect(eakay);

        logger.info(eakays.get(0).getName());

        List<Eakay>  eakays2=eakayService.slaveSelect(eakay);

        logger.info(eakays2.get(0).getName());

    }

    @Test
    public void testPageHepler()
    {
        PageInfo<List<Eakay>> pageInfo= eakayService.masterSelectAll();
        logger.info("--------------"+pageInfo.toString());

        PageInfo<List<Eakay>> slavePageInfo= eakayService.slaveSelectAll();
        logger.info("--------------"+slavePageInfo.toString());
    }

    @Test
    public void testKey()
    {
        Eakay eakay=new Eakay();
        eakay.setId(1);
       Eakay e= eakayService.selectByPrimaryKey(eakay);
       logger.info(e.getName());
    }

    @Test
    public void testExample()
    {
      List<Eakay> list= eakayService.selectByExample(null);
      logger.info(String.valueOf(list.size()));
    }




}
