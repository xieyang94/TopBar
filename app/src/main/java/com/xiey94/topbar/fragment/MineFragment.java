package com.xiey94.topbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiey94.topbar.base.BaseFragment;
import com.xiey94.topbar.R;

/**
 * @author xiey
 * @date created at 2018/3/18 13:08
 * @package com.xiey94.topbar.fragment
 * @project TopBar
 * @email xiey94@qq.com
 * @motto Why should our days leave us never to return?
 */

public class MineFragment extends BaseFragment {

    public static final String ARGUMENT = "MineFragment";
    View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_mine, container, false);
        return root;
    }

    /**
     * 实例化
     */
    public static MineFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(bundle);
        return mineFragment;
    }
}
