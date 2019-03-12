package sc.com.scdataview.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import sc.com.commonlib.impl.http.entity.CheckUpdate;
import sc.com.scdataview.R;

/**
 * Created by Eric on 2018/5/21.
 */
public class NeedUpdateDialog extends Dialog {
    private TextView version_focus, need_update, confirm_text, cancel_text;


    public NeedUpdateDialog(@NonNull Context context , CheckUpdate checkUpdate,OnDialogButtonClick click) {
        super(context);
        setContentView(R.layout.dialog_need_update);
        this.setCanceledOnTouchOutside(false);
        version_focus = findViewById(R.id.version_focus);
        need_update = findViewById(R.id.need_update);
        confirm_text = findViewById(R.id.confirm_text);
        cancel_text = findViewById(R.id.cancel_text);
        //设置可以滚动
        need_update.setText(String.format(context.getResources().getString(R.string.update_ticker),checkUpdate.getVersion_name()));
        version_focus.setMovementMethod(ScrollingMovementMethod.getInstance());
        version_focus.setText(checkUpdate.getVersion_focus());
        confirm_text.setOnClickListener((view)->click.onConfirmClick(this));
        cancel_text.setOnClickListener((view)->this.dismiss());

        if(checkUpdate.needForceUpdate()){
            cancel_text.setVisibility(View.GONE);
        }



    }



   public interface OnDialogButtonClick{
        void onConfirmClick(Dialog dialog);
    }
}
