package com.kozitski.xml.controller;

import static com.kozitski.xml.command.PathConstant.INDEX_JSP;

public class Router {

    public enum RouteType{
        FORWARD, REDIRECT
    }

    private String pagePath;
    private RouteType routeType;

    public Router() {
        pagePath = INDEX_JSP;
        routeType = RouteType.FORWARD;
    }

    String getPagePath() {
        return pagePath;
    }
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
    public RouteType getRouteType() {
        return routeType;
    }
    public void setRouteType(RouteType routeType) {
        if(routeType != null){
            this.routeType = routeType;
        }
    }

    boolean isForward(){
        return routeType.getClass() == RouteType.FORWARD.getClass();
    }

}
