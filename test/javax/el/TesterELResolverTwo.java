
package javax.el;

public class TesterELResolverTwo extends TypeConverter {

    @Override
    public Object convertToType(ELContext context, Object obj, Class<?> type) {
        if ("2".equals(obj) && type == String.class) {
            context.setPropertyResolved(obj, type);
            return "TWO";
        }
        return null;
    }
}
