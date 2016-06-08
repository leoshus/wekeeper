package com.sdw.soft.demo.javassist;

import javassist.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by shangyindong on 2016/6/8.
 */
public class JavassistDemo {

    @Ignore
    @Test
    public void testExtensionClass(){
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass cc = pool.get("com.sdw.soft.demo.htmlunit.HtmlunitTest");
            cc.setSuperclass(pool.get("com.sdw.soft.demo.dubbo.DubboConsumer"));
            cc.writeFile();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Javassist不支持要创建的或注入的类中存在泛型参数
     * Javassist对@类型的注解(annotation)只支持查询 不支持添加或修改
     */
    @Test
    public void testMakeClass(){
        //创建类
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("com.sdw.test.Person");
        try {
            //添加私有成员name以及getter、setter方法
            CtField cf = new CtField(pool.get("java.lang.String"),"name",cc);
            cf.setModifiers(Modifier.PRIVATE);
            cc.addMethod(CtNewMethod.setter("setName",cf));
            cc.addMethod(CtNewMethod.getter("getName",cf));
            cc.addField(cf, CtField.Initializer.constant(""));
//            CtMethod cm = CtNewMethod.make("public String getName(){return name;}",cc);
//            cc.addMethod(cm);
            //添加无参构造器
            CtConstructor ctc = new CtConstructor(new CtClass[]{},cc);
            ctc.setBody("{name = \"Tom\";}");
            cc.addConstructor(ctc);

            //添加有参构造器
            CtConstructor ctcParam = new CtConstructor(new CtClass[]{pool.get("java.lang.String")},cc);
            ctcParam.setBody("{$0.name = $1;}");
            cc.addConstructor(ctcParam);

            //打印创建类的类名
            System.out.println(cc.toClass());

            //通过反射创建无参的实例 并调用getName方法
            Object o = Class.forName("com.sdw.test.Person").newInstance();
            Method getter = o.getClass().getMethod("getName");
            System.out.println(getter.invoke(o));

            //调用其setName方法
            Method setter = o.getClass().getMethod("setName",new Class[]{String.class});
            setter.invoke(o,"Tom");
            System.out.println(getter.invoke(o));

            //通过反射创建有参的实例 并调用getName方法
            o = Class.forName("com.sdw.test.Person").getConstructor(String.class).newInstance("Rose");
            getter = o.getClass().getMethod("getName");
            System.out.println(getter.invoke(o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
