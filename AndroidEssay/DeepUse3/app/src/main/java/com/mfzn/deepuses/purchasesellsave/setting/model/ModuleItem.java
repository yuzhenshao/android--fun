package com.mfzn.deepuses.purchasesellsave.setting.model;

/**
 * @author yz @date 2020-03-24
 */
public class ModuleItem {
    private String title;
    private String content;
    private int iconRedId;
    private Class turnToActivity;

    public ModuleItem(String title,String content,int iconRedId,Class turnToActivity){
        this.title=title;
        this.content=content;
        this.iconRedId=iconRedId;
        this.turnToActivity=turnToActivity;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIconRedId() {
        return iconRedId;
    }

    public void setIconRedId(int iconRedId) {
        this.iconRedId = iconRedId;
    }

    public Class getTurnToActivity() {
        return turnToActivity;
    }

    public void setTurnToActivity(Class turnToActivity) {
        this.turnToActivity = turnToActivity;
    }
}
