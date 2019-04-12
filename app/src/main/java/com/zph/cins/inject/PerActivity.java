package com.zph.cins.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 *
 * @author zph
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
