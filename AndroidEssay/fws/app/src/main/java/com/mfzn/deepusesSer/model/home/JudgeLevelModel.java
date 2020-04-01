package com.mfzn.deepusesSer.model.home;

import java.util.List;

public class JudgeLevelModel {

    /**
     * roleID : 1
     * departIDs : ,236,237,238,393,394,395
     * menu : [{"menuID":1,"menuType":"审批报销","menuName":"费用报销"}]
     * authCreate : 1
     */

    private int roleID;
    private String departIDs;
    private int authCreate;
    private List<MenuBean> menu;

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getDepartIDs() {
        return departIDs;
    }

    public void setDepartIDs(String departIDs) {
        this.departIDs = departIDs;
    }

    public int getAuthCreate() {
        return authCreate;
    }

    public void setAuthCreate(int authCreate) {
        this.authCreate = authCreate;
    }

    public List<MenuBean> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuBean> menu) {
        this.menu = menu;
    }

    public static class MenuBean {
        /**
         * menuID : 1
         * menuType : 审批报销
         * menuName : 费用报销
         */

        private int menuID;
        private String menuType;
        private String menuName;

        public int getMenuID() {
            return menuID;
        }

        public void setMenuID(int menuID) {
            this.menuID = menuID;
        }

        public String getMenuType() {
            return menuType;
        }

        public void setMenuType(String menuType) {
            this.menuType = menuType;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }
    }
}
