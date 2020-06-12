package com.mfzn.deepuses.purchasesellsave.setting.manager;

import com.mfzn.deepuses.purchasesellsave.setting.model.StoreWarnModel;

import java.util.ArrayList;
import java.util.List;

public class StoreWarnManager {

    public static StoreWarnManager INSTANCE;
    protected List<StoreWarnModel> storeWarnModels = new ArrayList<>();
    protected List<StoreWarnModel> storeModels = new ArrayList<>();

    public static StoreWarnManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StoreWarnManager();
        }
        return INSTANCE;
    }

    public void addStoreSet(String storeId, String max, String min) {
        StoreWarnModel model = new StoreWarnModel(storeId, max, min);
        int index = storeWarnModels.indexOf(model);
        if (index > -1) {
            storeWarnModels.set(index, model);
        } else {
            storeWarnModels.add(model);
        }
    }


    public StoreWarnModel getWarnStoreModel(String storeId) {
        int index = storeWarnModels.indexOf(new StoreWarnModel(storeId));
        if (index > -1) {
            return storeWarnModels.get(index);
        }
        return null;
    }

    public List<StoreWarnModel> getStoreWarnModels() {
        return storeWarnModels;
    }

    public void addStoreSet(String storeId, String number) {
        StoreWarnModel model = new StoreWarnModel(storeId, number);
        int index = storeModels.indexOf(model);
        if (index > -1) {
            storeModels.set(index, model);
        } else {
            storeModels.add(model);
        }
    }

    public StoreWarnModel getStoreModel(String storeId) {
        int index = storeModels.indexOf(new StoreWarnModel(storeId));
        if (index > -1) {
            return storeModels.get(index);
        }
        return null;
    }

    public List<StoreWarnModel> getStoreModels() {
        return storeModels;
    }

}