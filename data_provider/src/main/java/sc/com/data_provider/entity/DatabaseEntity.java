package sc.com.data_provider.entity;

import java.io.Serializable;

/**
 * Created by Eric on 2018/4/10.
 */

public  class DatabaseEntity implements Serializable{

    protected static final long serialVersionUID = 1L;

    private int id;
    private String[] selectionArg;
    private String[] projection;

    private DatabaseEntity(String[] selectionArg, String[] projection) {
        this.selectionArg = selectionArg;
        this.projection = projection;
    }

    public DatabaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getSelectionArg() {
        return selectionArg;
    }

    public String[] getProjection() {
        return projection;
    }

    //查找相似
    public static DatabaseEntity getShipNameQueryLikeEntity(String query_text){

        return new DatabaseEntity(new String[]{"%"+query_text+"%"},null);
    }
    //查找
    public static DatabaseEntity getShipNameQueryEntity(String ship_id){

        return new DatabaseEntity(new String[]{ship_id},null);
    }
}
