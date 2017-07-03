package mvp.cn.common;


import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 *
 */
public class QuickActivity extends FragmentActivity {

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        QuickApplication.getInstance().getRefWatcher().watch(this);
    }
}
