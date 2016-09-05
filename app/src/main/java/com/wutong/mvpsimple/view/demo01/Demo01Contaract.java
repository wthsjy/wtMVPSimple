package com.wutong.mvpsimple.view.demo01;

import com.wutong.mvpsimple.base.presenter.IPresenter;
import com.wutong.mvpsimple.base.view.IView;

/**
 * Created by 吴同 on 2016/8/16 0016.
 */
public class Demo01Contaract {

    public interface IDemo01View extends IView {
        void loadSuccess();
    }

    public interface IDemo01Presenter extends IPresenter {
        void loadTestData();

    }
}
