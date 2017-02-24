package com.ybt.common.util;  
  
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
  
/** 
 *  List按照指定字段排序工具类 
 * 
 * @param <T> 
 */  
  
public class ListSortUtil<T> {  
    /** 
     * @param targetList 目标排序List 
     * @param sortField 排序字段(实体类属性名) 
     * @param sortMode 排序方式（asc or  desc） 
     */  
  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void sort(List<T> targetList, final String sortField, final String sortMode) {  
      
        Collections.sort(targetList, new Comparator() {  
            public int compare(Object obj1, Object obj2) {   
                int retVal = 0;  
                try {  
                    //首字母转大写  
                    String newStr=sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");   
                    String methodStr="get"+newStr;  
                      
                    Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);  
                    Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);  
                    if (sortMode != null && "desc".equals(sortMode)) {  
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序  
                    } else {  
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 正序  
                    }  
                } catch (Exception e) {  
                    throw new RuntimeException();  
                }  
                return retVal;  
            }  
        });  
    }  
      
    
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
  
          
        List<Video> targetList = new ArrayList<Video>();  
        targetList.add(new Video(1,"title1",31,new Date(2016, 02, 01)));  
        targetList.add(new Video(2,"title2",12,new Date(2016, 01, 01)));  
        targetList.add(new Video(3,"title3",53,new Date()));  
        System.out.println("排序前: " + targetList);  
           
        ListSortUtil<Video> sortList = new ListSortUtil<Video>();  
        sortList.sort(targetList, "date", "desc");  
        System.out.println("排序后：" +targetList);  
        System.out.println(new Date().getTime());
    }  
    
    
    /** 
     * 实体类 
     * 
     */  
    public static class Video {  
      
        public Video(int id, String title, int hits, Date date) {
    		super();
    		this.id = id;
    		this.title = title;
    		this.hits = hits;
    		this.date = date;
    	}
    	private int id;  
        private String title;  
        private int hits;
        private Date date;
        public int getId() {  
            return id;  
        }  
        public void setId(int id) {  
            this.id = id;  
        }  
        public String getTitle() {  
            return title;  
        }  
        public void setTitle(String title) {  
            this.title = title;  
        }  
        public int getHits() {  
            return hits;  
        }  
        public void setHits(int hits) {  
            this.hits = hits;  
        }
    	public Date getDate() {
    		return date;
    	}
    	public void setDate(Date date) {
    		this.date = date;
    	}  
      
        
    }  
}  