package webserver;

public class ViewResolver {
    public MyView resolve(String viewName) {
        if(isTemplate(viewName)||isStatic(viewName)){
            return new MyView(viewName);
        }

        return new MyView(viewName + ".html");
    }

    public Boolean isTemplate(String url){
        return url.endsWith(".html");
    }
    public Boolean isStatic(String url){
        return url.startsWith("/css/")||url.startsWith("/fonts/")||url.startsWith("/images/")||url.startsWith("/js/");
    }
}