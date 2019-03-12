package sc.com.data_provider.async;

import android.os.AsyncTask;

import sc.com.data_provider.provider.api.BaseProvider;

/**
 * Created by Eric on 2018/4/10.
 */

public class DatabaseAsyncTask<T extends BaseProvider> extends AsyncTask<T,Float,Object> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected Object doInBackground(T[] ts) {
        Object data = null;
        if(ts == null){
            return -1;
        }
        if(ts.length == 0){
            return -1;
        }

        return data;
    }



}
