package com.wt.load.container.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class WTLoadContainerView extends FrameLayout {

    private static final String TAG = WTLoadContainerView.class.getSimpleName();
    private Context mContext;


    /**
     * ChildDataView 异常类
     */
    public static class NoChildDataViewIdException extends IllegalArgumentException {
        public NoChildDataViewIdException(String detailMessage) {
            super(detailMessage);
        }
    }

    /**
     * 点击事件监听
     */
    public interface LoadContainerActionListener {
        void onNetWorkErrorViewClick();

        void onNoDataViewClick();
    }


    private boolean showLoadingViewInitialize = false;
    private LoadContainerActionListener mListener;
    /**
     * 加载中
     */
    private int loadingViewResId = R.layout.wt_load_detault_data_loading_view;
    private View loadingView;
    /**
     * 没有内容时显示的View
     */
    private View noDataView;
    private int noDataViewResId = R.layout.wt_load_detault_no_data_view;
    /**
     * 网络异常时显示的View
     */
    private View netErrorView;
    private int netErrorViewResId = R.layout.wt_load_detault_network_error_view;

    /**
     * 子类容器id，如有多个子View，请一定用viewGroup或ViewGroup 子类进行包裹，否则出现问题
     */
    private int childDataViewId;
    /**
     * 子类容器
     */
    private View childDataView;

    public WTLoadContainerView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public WTLoadContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public WTLoadContainerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mContext = context;

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.WTLoadContainerView, defStyle, 0);

        showLoadingViewInitialize = a.getBoolean(R.styleable.WTLoadContainerView_showLoadingViewInitialize, false);


        if (a.hasValue(R.styleable.WTLoadContainerView_dataLoadingView)) {
            loadingViewResId = a.getResourceId(
                    R.styleable.WTLoadContainerView_dataLoadingView, R.layout.wt_load_detault_data_loading_view);

        }
        if (a.hasValue(R.styleable.WTLoadContainerView_noDataView)) {
            noDataViewResId = a.getResourceId(
                    R.styleable.WTLoadContainerView_noDataView, R.layout.wt_load_detault_no_data_view);

        }

        if (a.hasValue(R.styleable.WTLoadContainerView_networkErrorView)) {
            netErrorViewResId = a.getResourceId(
                    R.styleable.WTLoadContainerView_networkErrorView, R.layout.wt_load_detault_network_error_view);
        }

        childDataViewId = a.getResourceId(R.styleable.WTLoadContainerView_childDataViewId, 0);
        a.recycle();

        if (childDataViewId == 0) {
            throw new NoChildDataViewIdException("childDataViewId must not be zero!");
        }


    }

    public void setLoadContainerActionListener(LoadContainerActionListener listener) {
        this.mListener = listener;
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (showLoadingViewInitialize) {
            showLoadingView();
        }
    }

    /**
     * 显示数据容器
     */
    public void showDataView() {
        //隐藏网络异常的View
        toggleNetErrorView(false);
        // 隐藏 没有数据View
        toggleNoDataView(false);
        // 隐藏 正在加载View
        toggleLoadingView(false);


        toggleChildDataView(true);
    }

    /**
     * 显示隐藏子容器
     *
     * @param show
     */
    private void toggleChildDataView(boolean show) {
        if (childDataView == null) {
            childDataView = findViewById(childDataViewId);
            if (childDataView == null) {
                throw new NoChildDataViewIdException("childDataView must not be null");
            }
        }
        childDataView.setVisibility(show ? VISIBLE : GONE);
    }

    /**
     * 显示 no data view
     */
    public void showNoDataView() {
        //隐藏网络异常的View
        toggleNetErrorView(false);
        // 隐藏 自定义数据View
        toggleChildDataView(false);
        // 隐藏 正在加载View
        toggleLoadingView(false);

        toggleNoDataView(true);
    }

    /**
     * 显示或隐藏 no data view
     *
     * @param show true 显示
     */
    private void toggleNoDataView(boolean show) {
        if (noDataViewResId == 0) {
            return;
        }
        if (show) {
            if (noDataView == null) {
                noDataView = LayoutInflater.from(mContext).inflate(noDataViewResId, null);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER;
                addView(noDataView, layoutParams);
                noDataView.setOnClickListener(new OnClickListener() {
                    @Override public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onNoDataViewClick();
                        }
                    }
                });
            }
            if (noDataView.getVisibility() != VISIBLE) {
                noDataView.setVisibility(VISIBLE);

            }

        } else {
            if (noDataView == null) {
                return;
            }
            if (noDataView.getVisibility() != GONE) {
                noDataView.setVisibility(GONE);
            }

        }

    }

    /**
     * 显示 no data view
     */
    public void showNetErrorView() {
        //隐藏没有数据的View
        toggleNoDataView(false);
        // 隐藏 自定义数据View
        toggleChildDataView(false);
        // 隐藏 正在加载View
        toggleLoadingView(false);

        toggleNetErrorView(true);

    }

    /**
     * 网络异常
     *
     * @param show true 显示
     */
    private void toggleNetErrorView(boolean show) {

        if (netErrorViewResId == 0) {
            return;
        }
        if (show) {
            if (netErrorView == null) {
                netErrorView = LayoutInflater.from(mContext).inflate(netErrorViewResId, null);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER;
                addView(netErrorView, layoutParams);
                netErrorView.setOnClickListener(new OnClickListener() {
                    @Override public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onNetWorkErrorViewClick();
                        }
                    }
                });
            }
            if (netErrorView.getVisibility() != VISIBLE) {
                netErrorView.setVisibility(VISIBLE);
            }
        } else {
            if (netErrorView == null) {
                return;
            }
            if (netErrorView.getVisibility() != GONE) {
                netErrorView.setVisibility(GONE);
            }
        }
    }

    /**
     * 显示正在加载View
     */
    public void showLoadingView() {
        //隐藏没有数据的View
        toggleNoDataView(false);
        // 隐藏 自定义数据View
        toggleChildDataView(false);
        // 隐藏 网络异常View
        toggleNetErrorView(false);

        toggleLoadingView(true);
    }

    /**
     * 正在加载中
     *
     * @param show true 显示
     */
    private void toggleLoadingView(boolean show) {

        if (loadingViewResId == 0) {
            return;
        }
        if (show) {
            if (loadingView == null) {
                loadingView = LayoutInflater.from(mContext).inflate(loadingViewResId, null);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER;
                addView(loadingView, layoutParams);
            }
            if (loadingView.getVisibility() != VISIBLE) {
                loadingView.setVisibility(VISIBLE);
            }
        } else {
            if (loadingView == null) {
                return;
            }
            if (loadingView.getVisibility() != GONE) {
                loadingView.setVisibility(GONE);
            }
        }
    }


}
