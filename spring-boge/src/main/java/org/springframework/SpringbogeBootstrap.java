package org.springframework;

import org.springframework.beans.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title: SpringbogeBootstrap
 * @Author xboge
 * @Date 2021-06-07 14:36
 * @Description: TODO
 */
public class SpringbogeBootstrap {
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath*:application.xml");
		Person person = context.getBean("person", Person.class);
		System.out.println(person.getName());
	}
}
