package com.mfzn.deepuses.bean.response.settings;

/**
 * @author yz @date 2020-05-03
 */
public class IncomeExpenseTypeResponse {

    /**
     * typeID : 1
     * typeName : 水电费
     * type : 1
     * addTime : 1583807323
     */

    public static int INCOME=1;
    public static int EXPENSE=2;
    private String typeID;
    private String typeName;
    private int type;//类型：1.收入；2.支出；
    private long addTime;

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
}
