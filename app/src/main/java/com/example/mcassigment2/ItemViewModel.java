package com.example.mcassigment2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {
    public MutableLiveData<String> selectedItem = new MutableLiveData<String >();

    public void setData(String item){
        selectedItem.setValue(item);
    }

    public LiveData<String>  getSelectedItems(){
        return selectedItem;
    }
}
