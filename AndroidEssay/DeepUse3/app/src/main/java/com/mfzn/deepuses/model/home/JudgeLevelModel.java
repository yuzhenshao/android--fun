package com.mfzn.deepuses.model.home;

import java.util.List;

public class JudgeLevelModel {


    /**
     * roleID : 1
     * departIDs : 493,494,495,496,497
     * menu : [{"menuID":1,"menuType":"审批报销","menuName":"费用报销"},{"menuID":2,"menuType":"审批报销","menuName":"申请加班"},{"menuID":3,"menuType":"审批报销","menuName":"产品采购"},{"menuID":4,"menuType":"审批报销","menuName":"物品领用"},{"menuID":5,"menuType":"审批报销","menuName":"我的申请"},{"menuID":6,"menuType":"审批报销","menuName":"我的审批"},{"menuID":7,"menuType":"审批报销","menuName":"请假调休"},{"menuID":8,"menuType":"日常管理","menuName":"签到"},{"menuID":9,"menuType":"日常管理","menuName":"企业云盘"},{"menuID":10,"menuType":"日常管理","menuName":"客户管理"},{"menuID":11,"menuType":"日常管理","menuName":"企业设置"},{"menuID":12,"menuType":"日常管理","menuName":"组织架构"}]
     * authCreate : 1
     * authManage : 1
     */

    private String roleID;
    private String departIDs;
    private String proCreateAuth;
    private String adminCreateAuth;
    private List<MenuBean> menu;

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getDepartIDs() {
        return departIDs;
    }

    public void setDepartIDs(String departIDs) {
        this.departIDs = departIDs;
    }

    public String getAuthCreate() {
        return proCreateAuth;
    }

    public void setAuthCreate(String authCreate) {
        this.proCreateAuth = authCreate;
    }

    public String getAuthManage() {
        return adminCreateAuth;
    }

    public void setAuthManage(String authManage) {
        this.adminCreateAuth = authManage;
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
