package Utils

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import base.BaseActiviy
import com.example.librarybaseresouse.R

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

class LoadingView(val context: BaseActiviy) {



    private var noDataView: View? = null
    private var loadingView: View? = null
    private var netInvalidView: View? = null

    fun initNetInvalidView() {
//        noDataView = View.inflate(context, R.layout.view_no_data, null)
        noDataView = ViewGroup.inflate(context,R.layout.view_no_data,null)
        noDataView!!.setLayoutParams(getLayoutParams())
    }


    fun initLoadingView() {
//        loadingView = View.inflate(context, R.layout.view_loading, null)
        loadingView = inflate(context,R.layout.view_loading)
        loadingView!!.setLayoutParams(getLayoutParams())
    }

    fun initNoDataView() {
//        netInvalidView = View.inflate(context, R.layout.view_net_invalid, null) as LinearLayout
        netInvalidView = ViewGroup.inflate(context,R.layout.view_net_invalid,null)
        netInvalidView!!.setLayoutParams(getLayoutParams())
        netInvalidView!!.findViewById<TextView>(R.id.tv_reload).setOnClickListener() {

            if (!isNetworkAvailable()) {
                //亲，您现在没网
                toast("亲，现在您没网")
            } else {
                //有网从新请求网络
                when (context) {
                    is BaseActiviy -> context.requestAgain()
                }
            }
        }
    }

    fun showOrHideLoadingView(isShow: Boolean) {
        if (loadingView == null) initLoadingView()
        if (isShow) {//先移除 防止重复添加导致程序崩溃
            //移除正在加载页面
            (context.window.decorView as FrameLayout).removeView(loadingView)
            //显示正在加载页面
            (context.window.decorView as FrameLayout).addView(loadingView)
        } else {
            //移除正在加载页面
            (context.window.decorView as FrameLayout).removeView(loadingView)
        }
    }

    /**
     * 展示或移除无数据视图
     *
     * @param isNoData 是否无数据
     */
    fun showOrHideNoDataView(isNoData: Boolean) {
        if (noDataView == null) initNoDataView()
        //无数据
        if (isNoData) {
            showOrHideLoadingView( false)
            //移除无数据页面 先移除再添加 防止重复添加导致程序崩溃
            (context.window.decorView as FrameLayout).removeView(noDataView)
            //显示无数据页面
            (context.window.decorView as FrameLayout).addView(noDataView)
        } else {
            //移除无数据页面
            (context.window.decorView as FrameLayout).removeView(noDataView)
        }
    }

    /**
     * 判断当前网络的状态  //判断当前网络情况  是否展示无网络界面，一般无用
     */
    private fun judgeNetWorkStatus() {
        //网络不可用
        if (!isNetworkAvailable()) {
            showOrHideLoadingView(false)
            showOrHideNoNetView(true)

        } else {
            showOrHideNoNetView( false)
            showOrHideLoadingView(true)
        }
    }

    /**
     * 是否显示无网络视图
     *
     * @param isShowNoNet
     */
    fun showOrHideNoNetView(isShowNoNet: Boolean) {

        if (netInvalidView == null) initNetInvalidView()
        if (isShowNoNet) {
            log("on_request_res")
            //移除无网页面 先移除再添加 防止程序崩溃
            (context.window.decorView as FrameLayout).removeView(netInvalidView)
            //显示无网页面
            (context.window.decorView as FrameLayout).addView(netInvalidView)
        } else {
            //移除无网页面
            (context.window.decorView as FrameLayout).removeView(netInvalidView)
        }
    }

}
