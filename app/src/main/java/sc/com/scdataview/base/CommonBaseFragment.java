package sc.com.scdataview.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eric on 2018/3/14.
 */

public abstract class CommonBaseFragment extends Fragment {

    protected View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getContentViewId(), container, false);
        onScFragmentCreateView(root);
        initView();
        return root;
    }

    protected abstract void onScFragmentCreateView(View rootView);

    protected abstract void initView();

    protected abstract int getContentViewId();

    protected <T extends View> T findScView(@IdRes int id) {
        T view = root.findViewById(id);
        return view;
    }

}
