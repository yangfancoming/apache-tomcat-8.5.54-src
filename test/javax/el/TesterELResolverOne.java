
package javax.el;

public class TesterELResolverOne extends TypeConverter {

    @Override
    public Object convertToType(ELContext context, Object obj, Class<?> type) {
        if ("1".equals(obj) && type == String.class) {
            context.setPropertyResolved(obj, type);
            return "ONE";
        }
        return null;
    }
}
