package com.gauravpatil.kindnesscabinet.java_classes;

public class POJOGetAllCategoryDetails
{
    // POJO => Plain old java object
    // reusebility
    // POJO class multiple data get and set

    String id,categoryimage,getCategoryname;
    public POJOGetAllCategoryDetails(String id, String categoryimage, String getCategoryname)
    {
        this.id = id;
        this.categoryimage = categoryimage;
        this.getCategoryname = getCategoryname;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCategoryimage()
    {
        return categoryimage;
    }

    public void setCategoryimage(String categoryimage)
    {
        this.categoryimage = categoryimage;
    }

    public String getCategoryname()
    {
        return getCategoryname;
    }

    public void setGetCategoryname(String getCategoryname)
    {
        this.getCategoryname = getCategoryname;
    }
}
