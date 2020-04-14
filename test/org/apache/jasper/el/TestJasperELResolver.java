
package org.apache.jasper.el;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.el.ELResolver;
import javax.servlet.jsp.el.ImplicitObjectELResolver;

import org.junit.Assert;
import org.junit.Test;

import org.apache.el.stream.StreamELResolverImpl;

public class TestJasperELResolver {

    @Test
    public void testConstructorNone() throws Exception {
        doTestConstructor(0);
    }

    @Test
    public void testConstructorOne() throws Exception {
        doTestConstructor(1);
    }

    @Test
    public void testConstructorFive() throws Exception {
        doTestConstructor(5);
    }

    private void doTestConstructor(int count) throws Exception {

        List<ELResolver> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new ImplicitObjectELResolver());
        }

        JasperELResolver resolver =
                new JasperELResolver(list, new StreamELResolverImpl());


        Assert.assertEquals(Integer.valueOf(count),
                getField("appResolversSize", resolver));
        Assert.assertEquals(9 + count,
                ((ELResolver[])getField("resolvers", resolver)).length);
        Assert.assertEquals(Integer.valueOf(9 + count),
                Integer.valueOf(((AtomicInteger) getField("resolversSize", resolver)).get()));
    }

    private static final Object getField(String name, Object target)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }
}
