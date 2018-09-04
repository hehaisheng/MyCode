package com.example.pc_.mycode.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc_.mycode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by pc- on 2017/8/13.
 */
public class OrderFragment extends BaseFragment {

    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.order_delete_pay)
    ImageView orderDeletePay;
    @BindView(R.id.order_progress)
    ProgressBar orderProgress;
    @BindView(R.id.order_content)
    TextView orderContent;
    @BindView(R.id.order_layout)
    RelativeLayout orderLayout;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.order_fragment;
    }

    @Override
    public void initInstance() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
