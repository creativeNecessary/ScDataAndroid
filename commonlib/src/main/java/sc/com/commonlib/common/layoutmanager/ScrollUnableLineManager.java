package sc.com.commonlib.common.layoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by Eric on 2018/3/29.
 */

public class ScrollUnableLineManager extends LinearLayoutManager {


    public ScrollUnableLineManager(Context context, int orientation) {
        super(context, orientation, false);
    }

    @Override
    public boolean canScrollHorizontally() {
        if( getOrientation() == LinearLayoutManager.HORIZONTAL){
            return false;
        }
        return super.canScrollHorizontally();

    }

    @Override
    public boolean canScrollVertically() {
        if( getOrientation() == LinearLayoutManager.VERTICAL){
            return false;
        }
        return super.canScrollVertically();
    }
}
