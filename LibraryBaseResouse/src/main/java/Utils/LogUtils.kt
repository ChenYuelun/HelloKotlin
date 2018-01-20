package Utils

import android.util.Log
import java.util.*

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


    private val TAG = "caiqrLog##"
    private var logCanPrint: Boolean = false

    fun initLogCanPrint(CanPrint: Boolean) {
        logCanPrint = CanPrint
    }
    /**
     * 打印字符串日志 String
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    fun log(tag: String, msg: String) {
        if (logCanPrint) {
            for (str in processTooLong(msg)) {
                Log.d(tag, str)
            }
        }
    }


    /**
     * 打印字符串日志 String
     *
     * @param msg 日志内容
     */
    fun log(msg: String) {
        if (logCanPrint) {
            for (str in processTooLong(msg)) {
                Log.d(TAG, str)
            }

        }
    }

    fun processTooLong(msg: String): List<String> {
        var index = 0
        val maxLength = 2048
        val countOfSub = msg.length / maxLength
        val result : MutableList<String> = mutableListOf()
        if (countOfSub > 0) {
            for (i in 0  .. countOfSub) {
               result.add(msg.substring(index, index + maxLength))
                index += maxLength
            }
        }
        result[countOfSub] = msg.substring(index, msg.length)
        return result
    }

    /**
     * 打印整形日志 int
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    fun log(tag: String, msg: Int) {
        if (logCanPrint) {
            Log.d(tag, msg.toString() + "")
        }

    }

    /**
     * 打印浮点型日志 float
     *
     * @param tag 日志TAG
     * @param msg 日志内容
     */
    fun log(tag: String, msg: Float) {
        if (logCanPrint) {
            Log.d(tag, msg.toString() + "")
        }

    }

    /**
     * 打印字符串数组日志 String[]
     *
     * @param tag   日志TAG
     * @param array 日志内容
     */
    fun log(tag: String, array: Array<String>) {
        if (logCanPrint) {
            Log.d(tag, Arrays.toString(array))
        }

    }

    /**
     * 打印整形数组日志 int[]
     *
     * @param tag   日志TAG
     * @param array 日志内容
     */
    fun log(tag: String, array: IntArray) {
        if (logCanPrint) {
            Log.d(tag, Arrays.toString(array))
        }

    }

    /**
     * 打印列表日志 List
     *
     * @param tag  日志TAG
     * @param list 日志内容
     */
    fun log(tag: String, list: List<*>) {
        if (logCanPrint) {
            Log.d(tag, list.toString())
        }

    }
