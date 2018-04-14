package cn.eakay.springboot.repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by magic~ on 2018/4/10.
 * tkmybatis 使用
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>
{
}
