/*   
 * Copyright (c) 2015-2020 *** Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ***. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ***.   
 *   
 */     
package com.ybt.common.util;    
    
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

public class CustomPropertyConfigurer extends PropertyPlaceholderConfigurer{
    private static Map<String,String> properties = new HashMap<String,String>();
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        // cache the properties
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(
                DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);
        for(Entry<Object,Object> entry:props.entrySet()){
            String stringKey = String.valueOf(entry.getKey());
            String stringValue = String.valueOf(entry.getValue());
            stringValue = helper.replacePlaceholders(stringValue, props);
            properties.put(stringKey, stringValue);
        }
        super.processProperties(beanFactoryToProcess, props);
    }
    
    public static Map<String, String> getProperties() {
        return properties;
    }
    
    public static String getProperty(String key){
        return properties.get(key);
    }
}