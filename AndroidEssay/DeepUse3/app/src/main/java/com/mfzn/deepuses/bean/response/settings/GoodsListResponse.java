package com.mfzn.deepuses.bean.response.settings;

import com.mfzn.deepuses.bean.request.CommodityRequest;

import java.util.List;

/**
 * @author yz @date 2020-05-03
 */
public class GoodsListResponse {

    /**
     * sumStockNum : 490
     * list : {"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"goodsID":1,"companyID":2,"shopID":1,"goodsName":"MacBook Pro","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"costPrice":"8888.00","salePrice":"9999.00","activeStoreWarning":1,"goodsImages":"1.jpg,2.jpg","goodsMainImage":"1.jpg","remark":"哈www","addTime":1586103699,"goodsSumStockNum":390},{"goodsID":2,"companyID":2,"shopID":1,"goodsName":"MacBook","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0002","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"costPrice":"8888.00","salePrice":"9999.00","activeStoreWarning":1,"goodsImages":"1.jpg,2.jpg","goodsMainImage":"1.jpg","remark":"哈哈哈","addTime":1586335877,"goodsSumStockNum":100}]}
     */

    private int sumStockNum;
    private ListBean list;

    public int getSumStockNum() {
        return sumStockNum;
    }

    public void setSumStockNum(int sumStockNum) {
        this.sumStockNum = sumStockNum;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * total : 2
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * data : [{"goodsID":1,"companyID":2,"shopID":1,"goodsName":"MacBook Pro","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0001","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"costPrice":"8888.00","salePrice":"9999.00","activeStoreWarning":1,"goodsImages":"1.jpg,2.jpg","goodsMainImage":"1.jpg","remark":"哈www","addTime":1586103699,"goodsSumStockNum":390},{"goodsID":2,"companyID":2,"shopID":1,"goodsName":"MacBook","goodsCatID":4,"catName":"Apple","goodsAttr":"最新的","goodsNum":"SP0002","goodsUnitID":3,"unitName":"台","goodsBarCode":"111111","goodsBrand":"Apple","goodsPosition":1,"costPrice":"8888.00","salePrice":"9999.00","activeStoreWarning":1,"goodsImages":"1.jpg,2.jpg","goodsMainImage":"1.jpg","remark":"哈哈哈","addTime":1586335877,"goodsSumStockNum":100}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<GoodsInfoResponse> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<GoodsInfoResponse> getData() {
            return data;
        }

        public void setData(List<GoodsInfoResponse> data) {
            this.data = data;
        }
    }
}
