package Utils

/**
 * Created by ${ChenYuelun} on 2017/12/3.
 *
 *----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 *
 *说明：
 */

    /**
     * dip转为 px
     */
    fun dip2px(dipValue: Float): Int {
        val scale = ActivityStack.GlobalContext().getResources().getDisplayMetrics().density
        return (dipValue * scale + 0.5f).toInt()
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale =  ActivityStack.GlobalContext().getResources().getDisplayMetrics().scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    fun sp2px(spValue: Float): Int {
        val fontScale =  ActivityStack.GlobalContext().getApplicationContext().getResources().getDisplayMetrics().scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

