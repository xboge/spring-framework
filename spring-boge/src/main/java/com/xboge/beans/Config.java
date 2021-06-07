package com.xboge.beans;

import org.springframework.context.annotation.ComponentScan;

//包扫描
@ComponentScan("com.xboge")
//@ImportResource("applicationContext.xml")//使用xml文件而非注解的方式进行启动
public class Config {
}