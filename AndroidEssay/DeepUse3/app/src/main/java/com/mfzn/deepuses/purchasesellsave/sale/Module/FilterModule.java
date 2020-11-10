package com.mfzn.deepuses.purchasesellsave.sale.Module;


public class FilterModule {

    private String typeId;
    private String typeName;

    public FilterModule(String typeId,String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
