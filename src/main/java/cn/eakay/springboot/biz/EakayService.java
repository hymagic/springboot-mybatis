package cn.eakay.springboot.biz;

import cn.eakay.springboot.client.Eakay;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by magic~ on 2018/4/12.
 */
public interface EakayService
{
     public List<Eakay> masterSelect(Eakay eakay);

     public List<Eakay> slaveSelect(Eakay eakay);

     public PageInfo<List<Eakay>> masterSelectAll();

     public PageInfo<List<Eakay>> slaveSelectAll();

     public Eakay selectByPrimaryKey(Eakay eakay);

     public Eakay selectOne(Eakay eakay);

     int selectCount(Eakay eakay);

     int insert(Eakay eakay);

     int insertSelective(Eakay eakay);

     int updateByPrimaryKey(Eakay eakay);

     int updateByPrimaryKeySelective(Eakay eakay);

     int delete(Eakay eakay);

     int deleteByPrimaryKey(Eakay eakay);

     List<Eakay> selectByExample(Eakay eakay);

     int selectCountByExample(Eakay eakay);

     int updateByExample(Eakay eakay);

     int updateByExampleSelective(Eakay eakay);

     int deleteByExample(Eakay eakay);



}
