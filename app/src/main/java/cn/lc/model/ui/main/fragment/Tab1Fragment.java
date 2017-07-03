package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.banner.anim.select.ZoomInEnter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.framework.widget.HorizontialListView;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.framework.widget.NoSlidingListView;
import cn.lc.model.framework.widget.SimpleImageBanner;
import cn.lc.model.framework.widget.bean.BannerItem;
import cn.lc.model.ui.main.model.Tab1Model;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.mywidget.NoticeView;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab1Fragment extends BaseFragment<Tab1Model,Tab1View,Tab1Presenter> {
    @BindView(R.id.et_search)
    TextView etSearch;
    @BindView(R.id.iv_msg)
    ImageView iv_msg;
    @BindView(R.id.iv_scan)
    ImageView iv_scan;
    @BindView(R.id.h_banner_viewPager)
    SimpleImageBanner sib;
    @BindView(R.id.gridView_catogary)
    NoSlidingGridView gridViewCatogary;
    @BindView(R.id.ll_recommend)
    LinearLayout llRecommend;
    @BindView(R.id.listView_recommend)
    HorizontialListView listViewRecommend;
    @BindView(R.id.ll_meal)
    LinearLayout llMeal;
    @BindView(R.id.listView_meal)
    HorizontialListView listViewMeal;
    @BindView(R.id.ll_love)
    LinearLayout llLove;
    @BindView(R.id.gridView_love)
    NoSlidingGridView gridViewLove;
    @BindView(R.id.ll_news)
    LinearLayout llNews;
    @BindView(R.id.listView_news)
    NoSlidingListView listViewNews;
    @BindView(R.id.notice)
    NoticeView notice;
    @BindView(R.id.id_swipe)
    SwipeRefreshLayout mSwipeLayout;

//
//    DocAdapter mTab1Adapter;
//    Tab1TaocanAdapter taocanAdapter;
//    Tab1LoveAdapter loveAdapter;
//    Tab1NewsAdapter newsAdapter;
//    CategoryAdapter mCategoryAdapter;
//    List<HomeBean> itemList;
//    List<HomeBean> categoryList;
//    List<String> list;
//
//    private Context context;
//
//
    List<BannerItem> imgList = new ArrayList<>();//banner
//    List<DoctorBean> docList = new ArrayList<>();//推荐专家


    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab1);
    }


    @Override
    public Tab1Presenter createPresenter() {
        return new Tab1Presenter();
    }

    @Override
    public Tab1Model createModel() {
        return null;
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        mSwipeLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        initRecommendList();//推荐专家
        initTaoCanList();//巨惠套餐
        initLoveList();//猜你喜欢
        initNewsList();//健康资讯
    }


    public void initBanner() {
        sib
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setSource(imgList)
                .startScroll();

    }

    public void initNotice() {
//        list = new ArrayList<>();
//        list = Arrays.asList(DataProvider.titles);
//        notice.setData(new NoticeView.ShowText() {
//            @Override
//            public String getText(int position) {
//                return list.get(position);
//            }
//
//            @Override
//            public int getSize() {
//                return list.size();
//            }
//        });
//        notice.setOnItemClickListener(new NoticeView.OnItemClickListener() {
//            @Override
//            public void click(int position) {
//                showToast(list.get(position).toString());
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (imgList.size() != 0)
//            sib.startScroll();
//        notice.start(3000, 3000);
    }



    @Override
    public void onPause() {
        super.onPause();
        sib.pauseScroll();
        notice.stop();
    }

    /**
     * 1-0 tab
     */
    private void initCatogary() {
//        mCategoryAdapter = new CategoryAdapter(getActivity());
//        mCategoryAdapter.setItemList(DataProvider.getCatogary());
//        gridViewCatogary.setAdapter(mCategoryAdapter);
//        gridViewCatogary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                        UIManager.turnToAct(getActivity(), TJReserveActivity.class);
//                        break;
//                    case 1:
//                        if (isLogin())
//                            UIManager.turnToAct(getActivity(), OnlineAskActivity.class);
//                        break;
//                    case 2:
//                        if (isLogin())
//                            UIManager.turnToAct(getActivity(), IndexDetectionActivity.class);
//                        break;
//                    case 3:
//                        if (!softApplication.isLogin()) {
//                            UIManager.turnToAct(getActivity(), LoginActivity.class);
//                            return;
//                        }
//                        UIManager.turnToAct(getActivity(), MallActivity.class);
//                        break;
//                }
//            }
//        });
    }

    /**
     * 1-1 推荐专家
     */
    private void initRecommendList() {

//        mTab1Adapter = new DocAdapter(getActivity());
//        mTab1Adapter.setItemList(docList);
//        listViewRecommend.setAdapter(mTab1Adapter);
//        listViewRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                DoctorBean bean = (DoctorBean) adapterView.getAdapter().getItem(i);
//                if (bean == null) {
//                    LogUtil.log("bean==null");
//                    return;
//                }
//                LogUtil.log("bean==" + bean.doc_id);
//                Bundle bundle = new Bundle();
//                bundle.putString("doctorId", bean.doc_id);
//                UIManager.turnToAct(getActivity(), DoctorDetailActivity.class, bundle);
//            }
//        });
    }

    /**
     * 1-2 巨惠套餐
     */
    private void initTaoCanList() {

//        taocanAdapter = new Tab1TaocanAdapter(getActivity());
//        taocanAdapter.setItemList(DataProvider.getRecommendList());
//        listViewMeal.setAdapter(taocanAdapter);
    }

    /**
     * 1-3 猜你喜欢
     */
    private void initLoveList() {

//        loveAdapter = new Tab1LoveAdapter(getActivity());
//        loveAdapter.setItemList(DataProvider.getRecommendList());
//        gridViewLove.setAdapter(loveAdapter);
    }

    /**
     * 1-3 健康资讯
     */
    private void initNewsList() {

//        newsAdapter = new Tab1NewsAdapter(getActivity());
//        newsAdapter.setItemList(DataProvider.getList());
//        listViewNews.setAdapter(newsAdapter);
    }


//    @OnClick({R.id.iv_scan,
//            R.id.ll_recommend,
//            R.id.rl_search,
//            R.id.ll_news,
//            R.id.oneKey_reserve,
//            R.id.iv_msg
//    })
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_scan:
//                UIManager.turnToAct(getActivity(), CaptureActivity.class);
//                break;
//            case R.id.rl_search:
//                UIManager.turnToAct(getActivity(), SearchActivity.class);
//                break;
//            case R.id.ll_news:
//                if (softApplication.isLogin()) {
//                    UIManager.turnToAct(getActivity(), NewsActivity.class);
//                } else {
//                    UIManager.turnToAct(getActivity(), LoginActivity.class);
//                }
//                break;
//            case R.id.ll_recommend:
//                break;
//            case R.id.oneKey_reserve:
//                UIManager.turnToAct(getActivity(), OneKeyActivity.class);
//                break;
//
//            case R.id.iv_msg://消息
//                if (softApplication.isLogin()) {
//                    UIManager.turnToAct(getActivity(), MessageListActivity.class);
//                } else {
//                    UIManager.turnToAct(getActivity(), LoginActivity.class);
//                }
//                break;
//        }
//
//
//    }


}
