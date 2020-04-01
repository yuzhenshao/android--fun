package com.wevey.selector.dialog.bean;

public class VipExplainBean {

    /**
     * level2 : {"level_id":2,"freeZhuan":"20","straitBonus":"15","interBonus":"10","continueBonusTime":"60","customService":"0","moduleNum":"4","recommendZhuan":"50","showMarket":"0","money":"288"}
     * level3 : {"level_id":3,"freeZhuan":"30","straitBonus":"20","interBonus":"15","continueBonusTime":"90","customService":"0","moduleNum":"5","recommendZhuan":"50","showMarket":"0","money":"388"}
     * level8 : {"level_id":8,"freeZhuan":"","straitBonus":"","interBonus":"","continueBonusTime":"","customService":"","moduleNum":"","recommendZhuan":"","showMarket":"","money":""}
     */

    public Level2Bean level2;
    public Level3Bean level3;
    public Level8Bean level8;

    public static class Level2Bean {
        /**
         * level_id : 2
         * freeZhuan : 20
         * straitBonus : 15
         * interBonus : 10
         * continueBonusTime : 60
         * customService : 0
         * moduleNum : 4
         * recommendZhuan : 50
         * showMarket : 0
         * money : 288
         */

        public int level_id;
        public String freeZhuan;
        public String straitBonus;
        public String interBonus;
        public String continueBonusTime;
        public String customService;
        public String moduleNum;
        public String recommendZhuan;
        public String showMarket;
        public String money;
    }

    public static class Level3Bean {
        /**
         * level_id : 3
         * freeZhuan : 30
         * straitBonus : 20
         * interBonus : 15
         * continueBonusTime : 90
         * customService : 0
         * moduleNum : 5
         * recommendZhuan : 50
         * showMarket : 0
         * money : 388
         */

        public int level_id;
        public String freeZhuan;
        public String straitBonus;
        public String interBonus;
        public String continueBonusTime;
        public String customService;
        public String moduleNum;
        public String recommendZhuan;
        public String showMarket;
        public String money;
        public String costRakeBack3;
    }

    public static class Level8Bean {
        /**
         * level_id : 8
         * freeZhuan : 
         * straitBonus : 
         * interBonus : 
         * continueBonusTime : 
         * customService : 
         * moduleNum : 
         * recommendZhuan : 
         * showMarket : 
         * money : 
         */

        public int level_id;
        public String freeZhuan;
        public String straitBonus;
        public String interBonus;
        public String continueBonusTime;
        public String customService;
        public String moduleNum;
        public String recommendZhuan;
        public String showMarket;
        public String money;
    }
}
