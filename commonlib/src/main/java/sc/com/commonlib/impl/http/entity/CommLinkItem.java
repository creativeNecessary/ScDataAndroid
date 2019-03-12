package sc.com.commonlib.impl.http.entity;

/**
 * Created by Eric on 2018/5/31.
 */
public class CommLinkItem extends SerializableBaseData {
    private int id;
    private String title;
    private String background;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
