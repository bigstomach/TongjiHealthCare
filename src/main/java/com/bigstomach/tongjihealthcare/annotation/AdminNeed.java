package com.bigstomach.tongjihealthcare.annotation;

import java.lang.annotation.*;

/**
 * Admin接口注解
 */

@java.lang.annotation.Target({ java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE })
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Inherited
@java.lang.annotation.Documented
public @interface AdminNeed {
}
