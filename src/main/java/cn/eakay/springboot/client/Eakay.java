package cn.eakay.springboot.client;

import tk.mybatis.mapper.entity.IDynamicTableName;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_eakay")
public class Eakay implements IDynamicTableName
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer eid;

    private String name;

    private String title;

    private Date createtime;

    private Date updatetime;
    @Transient
    private String dynamicTableName;


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return eid
     */
    public Integer getEid() {
        return eid;
    }

    /**
     * @param eid
     */
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String getDynamicTableName()
    {
        return dynamicTableName;
    }

    public void setDynamicTableName(String dynamicTableName)
    {
        this.dynamicTableName = dynamicTableName;
    }
}