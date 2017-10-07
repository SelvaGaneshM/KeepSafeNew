package com.abhinav.keepsafe.data;

import com.abhinav.keepsafe.pojo.Category;

import java.util.List;

/**
 * Created by Abhinav on 23/04/17.
 */
public class DataManager implements IDataManager {

    private PreferenceHelper mPreferenceHelper;
    private DataBaseHelper mDataBaseHelper;
    private static volatile DataManager mDataManager;

    private DataManager() {
        if (mDataManager!=null)
            throw new IllegalStateException("Illegal state of DataManager");
        else {
            // initialize all data sources;
            mPreferenceHelper = new PreferenceHelper();
            mDataBaseHelper = DataBaseHelper.getInstance();
        }
    }

    public static DataManager getInstance() {
        if (mDataManager == null){
            synchronized (DataManager.class){
                if (mDataManager == null){
                    mDataManager = new DataManager();
                }
            }
        }
        return mDataManager;
    }


    @Override
    public void savePin(String pin) {
        mPreferenceHelper.saveUserPin(pin);
    }

    @Override
    public String getSavedPin() {
        return mPreferenceHelper.getUserPin();
    }

    @Override
    public List<Category> fetchAllCategories() {
        return mDataBaseHelper.getAllCategories();
    }
}
