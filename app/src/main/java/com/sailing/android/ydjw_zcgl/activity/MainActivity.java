package com.sailing.android.ydjw_zcgl.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.sailing.android.ydjw_zcgl.R;
import com.sailing.android.ydjw_zcgl.base.BaseActivity;
import com.sailing.android.ydjw_zcgl.fragment.CheckAssetFragment;
import com.sailing.android.ydjw_zcgl.fragment.DistributeAssetFragment;
import com.sailing.android.ydjw_zcgl.fragment.ResearchAssetFragment;
import com.sailing.android.ydjw_zcgl.fragment.TotalAssetFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main_model)
    ViewPager vpMainModel;
    @BindView(R.id.tab_main_bottom)
    CommonTabLayout tabMainBottom;
    @BindView(R.id. draw_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.menu_main_navigation)
    NavigationView navigationView;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[]{"资产核查", "资产调研", "资产入库", "资产分配"};
    private int[] mIconUnselectIds = {
            R.mipmap.research, R.mipmap.checklist, R.mipmap.inbox,
            R.mipmap.subtotal};
    private int[] mIconSelectIds = {
            R.mipmap.research_select, R.mipmap.checklist_select, R.mipmap.inbox_select,
            R.mipmap.subtotal_select};

    private CheckAssetFragment checkAssetFragment;
    private DistributeAssetFragment distributeAssetFragment;
    private ResearchAssetFragment researchAssetFragment;
    private TotalAssetFragment totalAssetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                return false;
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.menu_main_navigation);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.navigation_item_profile) {
                    Intent intent = new Intent(mContext, SettingActivity.class);
                    startActivity(intent);
                }
                /*if (menuItem.getItemId() == R.id.navigation_item_checkhis) {
                    Intent intent = new Intent(mContext, CheckHistoryActivity.class);
                    startActivity(intent);
                }
                if (menuItem.getItemId() == R.id.navigation_item_memo) {
                    Intent intent = new Intent(mContext, MemoActivity.class);
                    startActivity(intent);
                }
                if (menuItem.getItemId() == R.id.navigation_item_offline) {
                    Intent intent = new Intent(mContext, OfflineDataActivity.class);
                    startActivity(intent);
                }
                *//*if (menuItem.getItemId() == R.id.navigation_item_hours) {
                    Intent intent = new Intent(mContext, AbnormalHoursActivity.class);
                    startActivity(intent);
                }*//*
                if (menuItem.getItemId() == R.id.navigation_item_hours_check) {
                    Intent intent = new Intent(mContext, HoursCheckActivity.class);
                    startActivity(intent);
                }*/
                return true;
            }
        });

        checkAssetFragment = new CheckAssetFragment();
        distributeAssetFragment = new DistributeAssetFragment();
        researchAssetFragment = new ResearchAssetFragment();
        totalAssetFragment = new TotalAssetFragment();

        for(int i = 0; i<mTitles.length;i++){
            mTabEntities.add(new TabEntity(mTitles[i],mIconSelectIds[i],mIconUnselectIds[i]));
        }

        vpMainModel.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return checkAssetFragment;
                }else if (position == 1) {
                    return researchAssetFragment;
                } else if (position == 2) {
                    return distributeAssetFragment;
                } else if (position == 3) {
                    return totalAssetFragment;
                }
                return checkAssetFragment;
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

        });

        vpMainModel.setOffscreenPageLimit(3);
        tabMainBottom.setTabData(mTabEntities);
        tabMainBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpMainModel.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vpMainModel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabMainBottom.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpMainModel.setCurrentItem(0);
    }

    public class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

    @Override
    protected boolean onBackQuit() {
        return true;
    }
}
