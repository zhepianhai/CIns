package com.zph.cins.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author zph
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {

}
