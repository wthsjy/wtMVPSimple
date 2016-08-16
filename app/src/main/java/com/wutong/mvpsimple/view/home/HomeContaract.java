package com.wutong.mvpsimple.view.home;

import com.wutong.mvpsimple.base.IPresenter;
import com.wutong.mvpsimple.base.IView;

/**
 * Created by 吴同 on 2016/8/15 0015.
 */
public class HomeContaract {

    public interface IHomeView extends IView {
        void loadSuccess();
    }

    public interface IHomePresenter extends IPresenter {
        void loadTestData();

    }


}
