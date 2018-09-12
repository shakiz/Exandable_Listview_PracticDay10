package com.exapnadablelistview.shakil.exandablelistview_practicday10;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private final SparseArray<Group> groupSparseArray;
    public LayoutInflater inflater;
    public Activity activity;

    //creating the constructor which takes two parameters activity , sparsearray
    public MyExpandableListAdapter(Activity activity,SparseArray<Group> sparseArray){
        this.activity=activity;
        this.groupSparseArray=sparseArray;
        inflater=activity.getLayoutInflater();
    }
    @Override
    public int getGroupCount() {
        return groupSparseArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupSparseArray.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupSparseArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //getting the object of chlidren position item
        return groupSparseArray.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.listrow_group,null);
        }
        Group group= (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return  convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //taking a string variable to get the child
        final String children= (String) getChild(groupPosition,childPosition);
        TextView text=null;
        //checking whether the view is null or not, if null then initialize it with layout
        if (convertView==null){
            convertView=inflater.inflate(R.layout.listrowdetails,null);
        }
        text=convertView.findViewById(R.id.textView1);
        text.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,children,Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
