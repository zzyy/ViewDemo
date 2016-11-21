package com.zy.md.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zy.md.R;
import com.zy.md.main.ui.BaseRecyclerAdapter;
import com.zy.md.main.ui.BaseRecyclerHolder;
import com.zy.md.main.ui.SheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 2016/11/21.
 */

public class TestBottomSheetDialog extends SheetDialog {


    public TestBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_bottom_sheet);
        initView();

        setPeekHeight(800);
        setMaxHeight(1500);
//        setBottomSheetCallback();
    }




    void initView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_dialog_root);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter( new Adapter() );
    }


}

class Adapter extends BaseRecyclerAdapter<String>{
    final static List<String> DATA = new ArrayList<>();

    static{
        for (int i =0; i<30; i++){
            DATA.add( i + " dialog item data" );
        }
    }
    public Adapter() {
        super(DATA, android.R.layout.simple_list_item_1);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position, String itemData) {
        TextView v = holder.getView(android.R.id.text1);

        v.setText( itemData );
    }
}

