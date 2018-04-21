package com.fpm.registry.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

@UtilityClass
public class Beans {

    @SneakyThrows
    public void populateNotNull(Object original, Object destination) {
        NullAwareBeanUtilsBean.getInstance().copyProperties(destination, original);
    }

    private static class NullAwareBeanUtilsBean extends BeanUtilsBean {

        private static final NullAwareBeanUtilsBean INSTANCE = new NullAwareBeanUtilsBean();

        NullAwareBeanUtilsBean() {
            super(new ConvertUtilsBean(), new PropertyUtilsBean() {

                @Override
                public PropertyDescriptor[] getPropertyDescriptors(Class<?> beanClass) {
                    return BeanUtils.getPropertyDescriptors(beanClass);
                }

                @Override
                public PropertyDescriptor getPropertyDescriptor(Object bean, String name) {
                    return BeanUtils.getPropertyDescriptor(bean.getClass(), name);
                }
            });
        }

        public static NullAwareBeanUtilsBean getInstance() {
            return INSTANCE;
        }

        @Override
        public void copyProperty(Object bean, String name, Object value)
                throws IllegalAccessException, InvocationTargetException {
            if (value == null) {
                return;
            }
            super.copyProperty(bean, name, value);
        }
    }
}
