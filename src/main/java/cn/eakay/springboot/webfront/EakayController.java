package cn.eakay.springboot.webfront;

import cn.eakay.springboot.biz.EakayService;
import cn.eakay.springboot.client.Eakay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by magic~ on 2018/4/12.
 */
@RestController
@RequestMapping(value = "/eakay")
public class EakayController
{
    @Autowired
    private EakayService eakayService;


    @RequestMapping(value = "/master/{id}",method = RequestMethod.GET)
    public List<Eakay> masterSelect(@PathVariable Integer id)
    {
           Eakay  eakay=new Eakay();
           eakay.setId(id);
       return  eakayService.masterSelect(eakay);

    }

    @RequestMapping(value = "/slave/{id}",method = RequestMethod.GET)
    public List<Eakay> slaveSelect(@PathVariable Integer id)
    {

        Eakay  eakay=new Eakay();
        eakay.setId(id);
        return  eakayService.slaveSelect(eakay);

    }



}
