package com.mfzn.deepuses.model.jiagou;

/**
 * @author yz @date 2020-04-25
 */
public class JiagouDataHelper {

    private static JiagouDataHelper INSTANCE;

    private ZuzhiJiagouModel mZuzhiJiagouModel;

    public static JiagouDataHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JiagouDataHelper();
        }
        return INSTANCE;
    }

    public JiagouDataHelper() {

    }
}
