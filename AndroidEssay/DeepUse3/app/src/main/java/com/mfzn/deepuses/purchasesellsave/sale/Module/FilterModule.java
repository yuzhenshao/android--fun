package com.mfzn.deepuses.purchasesellsave.sale.Module;


public class FilterModule {

    private Integer typeId;
    private String typeName;

    public FilterModule(Integer typeId,String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
