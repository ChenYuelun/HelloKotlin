package base

import Utils.ActivityStack
import Utils.LoadingView
import Utils.isNetworkAvailable
import Utils.log
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 *　　　　　　　 ┏┓　 ┏┓+ +
 *　　　　　　　┏┛┻━━━┛┻┓ + +
 *　　　　　　　┃　　　　　　┃ 　
 *　　　　　　　┃　　　━　　 ┃ ++ + + +
 *　　　　　　 ████━████  ┃+
 *　　　　　　　┃　　　　　　　┃ +
 *　　　　　　　┃　　　┻　　　┃
 *　　　　　　　┃　　　　　　┃ + +
 *　　　　　　　┗━┓　　　┏━┛
 *　　　　　　　　 ┃　　　┃　　　　　　　　　　　
 *　　　　　　　　 ┃　　　┃ + + + +
 *　　　　　　　　 ┃　　　┃　　　　Code is far away from bug with the animal protecting　　　　　　　
 *　　　　　　　　 ┃　　　┃ + 　　　　神兽保佑,代码无bug　　
 *　　　　　　　　 ┃　　　┃
 *　　　　　　　　 ┃　　　┃　　+　　　　　　　　　
 *　　　　　　　　 ┃　 　 ┗━━━┓ + +
 *　　　　　　　　 ┃ 　　　　   ┣┓
 *　　　　　　　　 ┃ 　　　　　 ┏┛
 *　　　　　　　　 ┗┓┓┏━┳┓┏┛ + + + +
 *　　　　　　　　  ┃┫┫ ┃┫┫
 *　　　　　　　　  ┗┻┛ ┗┻┛+ + + +
 *
 * Created by ChenYuelun on 2017/12/2.
 *
 * 说明：
 */
open class BaseActiviy: AppCompatActivity() {

    val mContext: Context = this
    private var onResumeStutas = 0
    val firstEnterInto = 0
    val noNetMaskOpen = 1
    val noNetMaskClose = 2
    val firstSendHttpNoBack = 3
    val laodingView = LoadingView(this)

    open var isNetNecessary = true //是否需要联网 在super.onCreate()之前调用

    open var mPagename = ""

    private var iterateOnResume = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
//        mPagename = this::class.simpleName!!//当前类名
//        log("thisPageName", mPagename)
        //添加activity的管理器中
        ActivityStack.addActivity(this)

        //需要联网获取数据
        if (isNetNecessary) {
            laodingView.showOrHideLoadingView(true)
        }
    }


    fun judgeFirstHttp() {

        if (!isNetNecessary) {//不需要网络请求
            return
        }
        log("request_interner", "是否第一次请求网络")
        log("request_interner", "onResumeStutas" + onResumeStutas)
        if (isNetworkAvailable()) {
            //如果是第一次请求数据
            if (onResumeStutas == firstEnterInto) {
                //是否在OnResume 重复请求部分接口
                iterateOnResume = sendFirstHttp()
                log("request_interner", "第一次请求网络")
                //先默认第一次请求数据失败
                onResumeStutas = firstSendHttpNoBack
            } else if (onResumeStutas == noNetMaskClose) {
                //在无网界面 重新打开网络请求数据
                log("request_interner", "无网界面点击请求")
                if (iterateOnResume) {//是否在onResume中重复请求第一次请求的数据
                    sendFirstHttp()
                    log("request_interner", "onResume中sendFirstHttp执行的重复请求")
                } else {
                    //此方法中是需要在onResume中重复请求的接口
                    sendIterateHttp()
                    log("request_interner", "onResume中sendIterateHttp执行的重复请求")
                }
            }
        } else {
            //如果没网 第一次请求数据显示无网界面，如果不是第一次请求，不显示无网界面
            if (onResumeStutas == firstEnterInto) {
                laodingView.showOrHideLoadingView(false)//移除loading界面
                laodingView.showOrHideNoNetView(true)//显示无网界面
                onResumeStutas = noNetMaskOpen
            }
        }
    }

    /**
     * 当请求数据成功调用此方法
     */
    fun onRequestSuccess() {
        if (onResumeStutas == noNetMaskOpen) {
            LoadingView(this).showOrHideNoNetView(false)
            onResumeStutas = noNetMaskClose
            //请求数据成功，状态改为无网络遮罩层关闭
        }

        log("request_interner", "数据请求成功")
        laodingView.showOrHideLoadingView(false)
    }

    /**
     * 当请求数据失败调用此方法
     */
    fun onRequestError() {
        log("request_interner", "请求失败：" + onResumeStutas)
        laodingView.showOrHideLoadingView(false)
        if (onResumeStutas == firstSendHttpNoBack) {
            //第一次请求数据失败,打开无网络遮罩层，状态改为无网络遮罩层开启
            laodingView.showOrHideNoNetView(true)
            onResumeStutas = noNetMaskOpen
            log("request_interner", "请求失败：" + onResumeStutas)
        }
    }

    /**
     * 返回的布尔值值是是否在onresume中重复发送此接口
     * true 在onresume中重复发送
     * false 只发送一次
     */
    fun sendFirstHttp(): Boolean {
        return false
    }

    /**
     * 如果两个及以上需要在进入页面发送的几口 其中部分需要在onresume中发送
     * 将部分需要重复发送的接口放入其中 并将sendFirstHttp返回值设置成false
     */
    fun sendIterateHttp() {}

    open fun requestAgain() {
        log("request_interner", "再次请求" + onResumeStutas)
        laodingView.showOrHideNoNetView(false)
        laodingView.showOrHideLoadingView(true)
        onResumeStutas = firstSendHttpNoBack
    }

    override fun onResume() {
        super.onResume()
        judgeFirstHttp()
    }

    override fun onDestroy() {
        super.onDestroy()
        //从activity管理器中移除
        ActivityStack.removeActivity(this)
    }


}