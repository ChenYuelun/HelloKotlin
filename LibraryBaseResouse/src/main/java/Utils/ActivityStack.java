package Utils;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * 全局activity的管理者
 */
public class ActivityStack {

    public static boolean needRestarWelcome = true;
    private static Application application;

    public static void initApplication(Application application){
        ActivityStack.application = application;
    }

    public static Application GlobalContext(){
        return application;
    }

    /**
     * 栈集合
     */
    private static Stack<Activity> activityStack = new Stack<>();

    /**
     * 添加activity到栈集合中
     *
     * @param activity 被添加的集合
     */
    public static void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 移除指定的activity
     *
     * @param activity 被移除的activity
     */
    public static void removeActivity(Activity activity) {
        activity.finish();
        activityStack.remove(activity);
    }

    /**
     * 销毁所有的activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 根据类名销毁activity
     *
     * @param cls 被销毁activity
     */
    public static void finishActivityByClass(Class<?> cls) {

        for (Activity activity : activityStack) {
            if (activity.getClass() == cls) {
                activity.finish();
            }
        }
    }
    /**
     * 根据类名销毁activity
     *
     * @param cls 被销毁activity
     */
    public static void finishActivityByClassInt(Class<?> cls) {
        int n=0;
        for (int i=0;i<activityStack.size();i++) {
            if (activityStack.get(i).getClass() == cls) {
                if (n!=0){
                    activityStack.get(i).finish();
                }
                n++;
            }
        }
    }


    /**
     * 获取顶部的activity
     *
     * @return 返回顶部的activity
     */
    public static Activity getTopActivity() {
        return activityStack.lastElement();
    }
    /**
     * 获取顶部的activity
     *
     * @return 返回顶部的activity
     */
    public static Activity getTopActivitys() {
        return activityStack.get(2);
    }

    /**
     * 获取顶部的activity
     *
     * @return 返回顶部的activity
     */
    public static Activity getTop2Activity() {
        return activityStack.get(activityStack.size()-2);
    }

}
