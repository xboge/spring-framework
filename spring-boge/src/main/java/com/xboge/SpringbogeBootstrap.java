package com.xboge;

import com.xboge.beans.Config;
import com.xboge.beans.Person;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xboge.service.PersonService;

/**
 * @Title: SpringbogeBootstrap
 * @Author xboge
 * @Date 2021-06-07 14:36
 * @Description: TODO
 */
public class SpringbogeBootstrap {
	public static void main(String[] args) {

		testAnnotation();

	}

	private static void testFileXml(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath*:application.xml");
		Person person = context.getBean("person", Person.class);
		System.out.println(person.getName());
	}

	private static void testAnnotation(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//Config.java  主要是注册配置类，告知扫描的包名或者配置文件等
		context.register(Config.class);
		//所有xml配置或者注解，均是为了生成BeanDefinition对象，也可以自己创建该对象，注册到spring容器中
		//context.registerBeanDefinition();
		context.refresh();

		//获取PersonService对应的BeanDefinition ，默认名称为personService，关于名字的更改以后讲。
		BeanDefinition interServiceBeanDefinition = context.getBeanDefinition("personService");

		System.out.println("——————InterService的附加属性如下：");
		System.out.println("父类"+interServiceBeanDefinition.getParentName());
		System.out.println("描述"+interServiceBeanDefinition.getDescription());
		System.out.println("InterService在spring的名称"+interServiceBeanDefinition.getBeanClassName());
		System.out.println("实例范围"+interServiceBeanDefinition.getScope());
		System.out.println("是否是懒加载"+interServiceBeanDefinition.isLazyInit());
		System.out.println("是否是抽象类"+interServiceBeanDefinition.isAbstract());
		System.out.println("——————等等等等，读者自行编写");

		PersonService service = context.getBean(PersonService.class);
		if(service != null){
			service.displayInfo();
		}
	}

	/**
	 * @Title: SpringbogeBootstrap
	 * @Author xboge
	 * @Date 2021-06-07 17:00
	 * @Description: spring默认允许非构造方法注入的循环依赖，但也可以关闭
	*/
	public static void testCircular(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		//获取bean工厂
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
		//关闭循环依赖
		beanFactory.setAllowCircularReferences(false);
		context.register(PersonService.class);
		context.refresh();
		System.out.println(context.getBean("orderService"));
	}
}
