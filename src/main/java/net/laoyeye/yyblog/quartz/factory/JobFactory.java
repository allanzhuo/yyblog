package net.laoyeye.yyblog.quartz.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;
@Component
public class JobFactory extends AdaptableJobFactory {
    //这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
    //为什么需要这个类呢，在我写的这个demo中，大家可以将此类删掉，发现程序也可以政策运营，可是我为什么还是加上呢。
    //大家可以看下我们的任务类，大家可以看到Job对象的实例化过程是在Quartz中进行的，这时候我们将spring的东西注入进来，肯定是行不通的，所以需要这个类
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
