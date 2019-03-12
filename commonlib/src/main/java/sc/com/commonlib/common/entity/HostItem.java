package sc.com.commonlib.common.entity;

/**
 * Created by Eric on 2018/5/24.
 */
public class HostItem {

    private String item_name;
    private int item_drawable;

    public HostItem(String item_name, int item_drawable) {
        this.item_name = item_name;
        this.item_drawable = item_drawable;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_drawable() {
        return item_drawable;
    }

    public void setItem_drawable(int item_drawable) {
        this.item_drawable = item_drawable;
    }
}
