//package sc.com.data_provider.entity;
//
//import sc.com.data_provider.database.ScDatabaseConstant;
//
///**
// * Created by Eric on 2018/4/10.
// */
//
//public class TranslateEntity extends DatabaseEntity {
//
//    private String type;
//    private String belong_to;
//    private String tag;
//    private String translate_value;
//
//
//
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getBelong_to() {
//        return belong_to;
//    }
//
//    public void setBelong_to(String belong_to) {
//        this.belong_to = belong_to;
//    }
//
//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }
//
//    public String getTranslate_value() {
//        return translate_value;
//    }
//
//    public void setTranslate_value(String translate_value) {
//        this.translate_value = translate_value;
//    }
//
//    @Override
//    public String[] getSelectionArg() {
//        String[] args = new String[3];
//        args[0] = getType();
//        args[1] = getBelong_to();
//        args[2] = getTag();
//
//        return args;
//    }
//
//    @Override
//    public String[] getProjection() {
//
//        return null;
//    }
//}
