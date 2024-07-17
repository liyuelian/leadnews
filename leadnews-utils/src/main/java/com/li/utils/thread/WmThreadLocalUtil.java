package com.li.utils.thread;

import com.li.model.wemedia.pojos.WmUser;

/**
 * @Author: liyuelian
 * @Date: 2024/7/16 21:33
 * @Description:
 **/
public class WmThreadLocalUtil {
    private final static ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    //存入线程中
    public static void setUser(WmUser wmUser) {
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    //从线程中获取
    public static WmUser getUser() {
        return WM_USER_THREAD_LOCAL.get();
    }

    //清理
    public static void clear() {
        WM_USER_THREAD_LOCAL.remove();
    }
}
