package com.xboge.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 扫描注册成功完成后，spring自动调用后置处理器MyBeanFactoryPostProcessor的postProcessBeanFactory方法
 * 执行bean工厂的后置处理器，这行代码完成了扫描与注册，我不带大家分析里面的代码，你只需要知道他的作用就行，这行代码执行完成后，我们只是把业务类InterService封装成了BeanDefinition而已，
 * 业务类InterService并没有实例化，在业务类InterService实例化之前我们能不能从beanDefinition中将InterService偷梁换柱呢？或者说，我们能通过BeanDefinition来构建bean，那我们能不能修改bean呢？那必须的！
 *        通过后置处理器完成，什么是后置处理器？可以把它理解成回调，我扫描注册成功后回调后置处理器！BeanDefinition讲完后紧接着就讲后置处理器。
 *
 * spring扫描注册完成后，会自动调用MyBeanFactoryPostProcessor的postProcessBeanFactory方法，这个方法给你传递了一个ConfigurableListableBeanFactory类型的bean工厂，
 * ConfigurableListableBeanFactory是一个接口，上文spring启动实例化的DefaultListableBeanFactory工厂是它的实现类。
 * getBeanDefinition方法返回的BeanDefinition类型，为什么强转成GenericBeanDefinition，起始BeanDefinition接口中并没有setBeanClass这个方法，GenericBeanDefinition是他的实现，提供更丰富的功能。
 * 不同的BeanDefinition实现具有不同的作用
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        //通过bean工厂拿到业务类InterService的beanDefinition
        GenericBeanDefinition beanDefinition =
                (GenericBeanDefinition) beanFactory.getBeanDefinition("interService");
        System.out.println("扫描注册成功完成后，spring自动调用次方法");
        System.out.println(beanDefinition.getDescription());
    }
}