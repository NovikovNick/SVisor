package ru.nick.unit;

import javax.inject.Inject;
import javax.inject.Named;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ru.nick.bo.impl.TestProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/spring-jpa.xml", "/spring-cashe.xml"})
public class TestTest extends Assert {
   
    
    @Inject
    @Named("testProvider")
    public TestProvider test;

    
    @Test
    public void launchJob() throws Exception {
        System.out.println(test);
    }
    
}
