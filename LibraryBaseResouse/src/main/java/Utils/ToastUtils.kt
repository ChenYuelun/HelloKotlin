package Utils

import android.text.TextUtils
import android.widget.Toast

/**
 * Created by ${ChenYuelun} on 2017/12/6.
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

    private var toast: Toast? = null
    private var toastBulider: Toast? = null


    /**
     * toast 字符串
     *
     * @param text    弹出的字符串
     */
    fun toast(text: String) {
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(text.trim { it <= ' ' }) || text.length == 0 || text.contains("异常")) {
            return
        }
        if (toast == null) {
            toast = Toast.makeText(ActivityStack.GlobalContext(), text, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(text)
        }
        toast!!.show()
    }

    /**
     * toast 字符串Id
     *
     * @param context Context
     * @param resId   弹出的字符串Id
     */
    fun toast(resId: Int) {
        val showText = ActivityStack.GlobalContext().resources.getString(resId)
        if (toast == null) {
            toast = Toast.makeText(ActivityStack.GlobalContext(), showText, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(showText)
        }

        toast!!.show()
    }

    /**
     * 返回toast 实例对象
     * @return
     */
    fun BuildToast(): Toast? {
        if (toastBulider == null) {
            toastBulider = Toast(ActivityStack.GlobalContext())
        }
        return toastBulider
    }
