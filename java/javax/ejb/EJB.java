


package javax.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface EJB {
   String name() default "";
   String description() default "";
   @SuppressWarnings("rawtypes") // Can't use Class<?> because API needs to match specification
   Class beanInterface() default java.lang.Object.class;
   String beanName() default "";
   String mappedName() default "";
   String lookup() default "";
}
