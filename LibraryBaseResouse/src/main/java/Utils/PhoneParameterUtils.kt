package Utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Point
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

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
    /**
     * 得到屏幕的宽度
     */
    fun getScreenWidth(): Int {
        val manager = ActivityStack.GlobalContext()
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        // 低于版本13的
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return display.width
        } else {
            val point = Point()
            display.getSize(point)
            return point.x
        }
    }

    /**
     * 得到屏幕的高度
     */
    fun getScreenHeight(): Int {
        val manager = ActivityStack.GlobalContext()
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        // 低于版本13的
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return display.height
        } else {
            val point = Point()
            display.getSize(point)
            return point.y
        }
    }


    @SuppressLint("WifiManagerLeak", "MissingPermission")
// WLAN MAC 地址
    fun getPhoneMacId(): String {
        @SuppressLint("WifiManagerLeak") val wifi = ActivityStack.GlobalContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
        @SuppressLint("MissingPermission") val info = wifi.connectionInfo
        return info.macAddress
    }

    @SuppressLint("MissingPermission")
//IMEI: 仅仅只对Android手机有效
    fun getPhoneIMEI(): String {
        try {
            val TelephonyMgr = ActivityStack.GlobalContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return TelephonyMgr.deviceId
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    //获取手机名称+型号
    fun getPhoneName(): String {
        return android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL
    }


    //获取手机唯一标识
    fun getPhoneId(): String {
        return getMD5Str(getPhoneIMEI() + getPhoneMacId())
    }


    //获取本App版本号
    fun getVersionName(): String {
        try {
            val pi = ActivityStack.GlobalContext().applicationContext.packageManager.getPackageInfo(ActivityStack.GlobalContext().applicationContext.packageName, 0)
            return pi.versionName + ""
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return ""
        }

    }

    //是否黑屏
    fun isScreenUnlocked(): Boolean {
        val mKeyguardManager = ActivityStack.GlobalContext().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        return !mKeyguardManager.inKeyguardRestrictedInputMode()
    }

    //程序是否可见
    fun isVisibleApp(packageName: String): Boolean {
        val activityManager = ActivityStack.GlobalContext().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val tasksInfo = activityManager.getRunningTasks(1)
        if (tasksInfo.size > 0) {
            // 应用程序位于堆栈的顶层
            if (packageName == tasksInfo[0].topActivity.packageName) {
                log("定时校验token", "彩球可见")
                return true
            } else {
                log("定时校验token", "彩球不可见")
            }
        }
        return false

    }


    //MD5加密
    fun getMD5Str(str: String): String {
        var messageDigest: MessageDigest? = null

        try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest!!.reset()
            messageDigest.update(str.toByteArray(charset("UTF-8")))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        val byteArray = messageDigest!!.digest()

        val md5StrBuff = StringBuffer()

        for (b in byteArray) {
            if (Integer.toHexString(0xFF and b.toInt()).length == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF and b.toInt()))
            else
                md5StrBuff.append(Integer.toHexString(0xFF and b.toInt()))
        }
        return md5StrBuff.toString()
    }

    // 判断是否是链接WiFi
    @SuppressLint("MissingPermission")
    private fun isWifi(mContext: Context): Boolean {
        val connectivityManager = mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
    }


    @SuppressLint("MissingPermission")
//判断网络是否连接可用
    fun isNetworkAvailable(): Boolean {
        val mConnectivityManager = ActivityStack.GlobalContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (mConnectivityManager != null) {
            val mNetworkInfo = mConnectivityManager
                    .activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable && mNetworkInfo.isConnected
            }
        }
        return false
    }

    //获取versionCode
    fun getVersionCode(): Int {
        try {
            val pi = ActivityStack.GlobalContext().packageManager.getPackageInfo(ActivityStack.GlobalContext().packageName, 0)
            return pi.versionCode
        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }

    }

    //4.4设置titleLayout padding
    fun setTitleLayout(titleLayout: View?) {
        if (null != titleLayout) {
            setStatusStyle(titleLayout)
        }
    }




    //判断小于4.4的手机 顶部不需要叫padding
    fun setStatusStyle(view: View) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return
        }
        view.setPadding(
                view.paddingLeft,
                getPhoneStatusBarHeight(ActivityStack.GlobalContext().resources) +view.paddingTop,
                view.right,
                view.bottom)
    }

    /**
     * 得到手机状态栏高度
     *
     * @param res 资源
     * @return 返回高度
     */
    fun getPhoneStatusBarHeight(res: Resources): Int {
        var result = 0
        val resourceId = res.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /**
     * 获取布局属性  base类中 无数据、无网 、加载中等页面
     *
     * @return LayoutParams
     */
    fun getLayoutParams(): FrameLayout.LayoutParams {
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        val margin = dip2px(50f) + getPhoneStatusBarHeight(ActivityStack.GlobalContext().resources)
        layoutParams.setMargins(0, margin, 0, 0)
        return layoutParams
    }
