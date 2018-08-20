package wang.jinggo.util;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * bean工具类
 * @author wangyj
 * @description
 * @create 2018-08-20 14:45
 **/
public class BeanField {

    String primaryKey;
    String className;
    Field[] fieldList;

    public BeanField(String primaryKey, String className, Field[] fieldList) {
        this.primaryKey = primaryKey;
        this.className = className;
        this.fieldList = fieldList;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Field[] getFieldList() {
        return fieldList;
    }

    public void setFieldList(Field[] fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        return "BeanField{" +
                "primaryKey='" + primaryKey + '\'' +
                ", className='" + className + '\'' +
                ", fieldList=" + Arrays.toString(fieldList) +
                '}';
    }
}
