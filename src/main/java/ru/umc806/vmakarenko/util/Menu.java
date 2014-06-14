package ru.umc806.vmakarenko.util;

/**
 * Created by VMakarenko on 5/20/14.
 */
public class Menu {
    private String url;
    private String name;

    public Menu(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return "["+name+", "+url+"]";
    }


}
