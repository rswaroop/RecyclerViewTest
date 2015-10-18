package swaroop.recyclerView;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import swaroop.recyclerView.utils.Logging;

public class LauncherActivity extends AppCompatActivity implements WebViewFragment.DataChangeListener {
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    public void onDataChanged() {
        RecordsFragment recordsFragment = (RecordsFragment) mViewPagerAdapter.getItem(mViewPagerAdapter.getCount() - 1);

        if (recordsFragment != null) {
            recordsFragment.onRecordsChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setDataChangeListener(this);
        RecordsFragment recordsFragment = new RecordsFragment();

        mViewPagerAdapter.addFragment(webViewFragment, getResources().getString(R.string.webview_tab));
        mViewPagerAdapter.addFragment(recordsFragment, getResources().getString(R.string.records_tab));

        viewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        Logging.init(true);
    }
}
