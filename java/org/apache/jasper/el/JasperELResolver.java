

package org.apache.jasper.el;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.PropertyNotFoundException;
import javax.el.ResourceBundleELResolver;
import javax.el.StaticFieldELResolver;
import javax.servlet.jsp.el.ImplicitObjectELResolver;
import javax.servlet.jsp.el.ScopedAttributeELResolver;

/**
 * Jasper-specific CompositeELResolver that optimizes certain functions to avoid
 * unnecessary resolver calls.
 */
public class JasperELResolver extends CompositeELResolver {

    private static final int STANDARD_RESOLVERS_COUNT = 9;

    private AtomicInteger resolversSize = new AtomicInteger(0);
    private volatile ELResolver[] resolvers;
    private final int appResolversSize;

    public JasperELResolver(List<ELResolver> appResolvers,
            ELResolver streamResolver) {
        appResolversSize = appResolvers.size();
        resolvers = new ELResolver[appResolversSize + STANDARD_RESOLVERS_COUNT];

        add(new ImplicitObjectELResolver());
        for (ELResolver appResolver : appResolvers) {
            add(appResolver);
        }
        add(streamResolver);
        add(new StaticFieldELResolver());
        add(new MapELResolver());
        add(new ResourceBundleELResolver());
        add(new ListELResolver());
        add(new ArrayELResolver());
        add(new BeanELResolver());
        add(new ScopedAttributeELResolver());
    }

    @Override
    public synchronized void add(ELResolver elResolver) {
        super.add(elResolver);

        int size = resolversSize.get();

        if (resolvers.length > size) {
            resolvers[size] = elResolver;
        } else {
            ELResolver[] nr = new ELResolver[size + 1];
            System.arraycopy(resolvers, 0, nr, 0, size);
            nr[size] = elResolver;

            resolvers = nr;
        }
        resolversSize.incrementAndGet();
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property)
        throws NullPointerException, PropertyNotFoundException, ELException {
        context.setPropertyResolved(false);

        int start;
        Object result = null;

        if (base == null) {
            // call implicit and app resolvers
            int index = 1 /* implicit */ + appResolversSize;
            for (int i = 0; i < index; i++) {
                result = resolvers[i].getValue(context, base, property);
                if (context.isPropertyResolved()) {
                    return result;
                }
            }
            // skip stream, static and collection-based resolvers (map,
            // resource, list, array) and bean
            start = index + 7;
        } else {
            // skip implicit resolver only
            start = 1;
        }

        int size = resolversSize.get();
        for (int i = start; i < size; i++) {
            result = resolvers[i].getValue(context, base, property);
            if (context.isPropertyResolved()) {
                return result;
            }
        }

        return null;
    }

    @Override
    public Object invoke(ELContext context, Object base, Object method,
            Class<?>[] paramTypes, Object[] params) {
        String targetMethod = coerceToString(method);
        if (targetMethod.length() == 0) {
            throw new ELException(new NoSuchMethodException());
        }

        context.setPropertyResolved(false);

        Object result = null;

        // skip implicit and call app resolvers, stream resolver and static
        // resolver
        int index = 1 /* implicit */ + appResolversSize +
                2 /* stream + static */;
        for (int i = 1; i < index; i++) {
            result = resolvers[i].invoke(
                    context, base, targetMethod, paramTypes, params);
            if (context.isPropertyResolved()) {
                return result;
            }
        }

        // skip collection (map, resource, list, and array) resolvers
        index += 4;
        // call bean and the rest of resolvers
        int size = resolversSize.get();
        for (int i = index; i < size; i++) {
            result = resolvers[i].invoke(
                    context, base, targetMethod, paramTypes, params);
            if (context.isPropertyResolved()) {
                return result;
            }
        }

        return null;
    }

    /*
     * Copied from org.apache.el.lang.ELSupport#coerceToString(ELContext,Object)
     */
    private static final String coerceToString(final Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof Enum<?>) {
            return ((Enum<?>) obj).name();
        } else {
            return obj.toString();
        }
    }
}
