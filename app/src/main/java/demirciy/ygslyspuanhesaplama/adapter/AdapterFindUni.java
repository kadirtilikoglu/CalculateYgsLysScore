package demirciy.ygslyspuanhesaplama.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demirciy.ygslyspuanhesaplama.R;

/**
 * Created by Yusuf on 11.11.2016.
 */

public class AdapterFindUni extends BaseExpandableListAdapter {

    Context context;
    HashMap<List<String>, ArrayList<String>> child;
    HashMap<Integer, List<String>> parent;

    public AdapterFindUni(Context context, HashMap<Integer, List<String>> parent, HashMap<List<String>, ArrayList<String>> child) {

        this.context = context;
        this.parent = parent;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(parent.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //parent(başlık) layoutu custom olduğu için tanımlamak gerekli
            convertView = inflater.inflate(R.layout.find_uni_parent, null);
        }

        ArrayList uni = (ArrayList) getGroup(groupPosition);

        TextView tUni = (TextView) convertView.findViewById(R.id.tUniName);
        tUni.setText(String.valueOf(uni.get(0)));
        TextView tDepart = (TextView) convertView.findViewById(R.id.tDepartName);
        tDepart.setText(String.valueOf(uni.get(1)));
        TextView tScoreType = (TextView) convertView.findViewById(R.id.tScoreType);
        tScoreType.setText(String.valueOf(uni.get(2)));
        TextView tScoreLow = (TextView) convertView.findViewById(R.id.tScoreLow);
        tScoreLow.setText(String.valueOf(uni.get(3)));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.find_uni_child, null);
        }

        TextView tQuota = (TextView) convertView.findViewById(R.id.tQuota);
        tQuota.setText(String.valueOf(getChild(groupPosition, childPosition)));
        TextView tLastScore = (TextView) convertView.findViewById(R.id.tLastScore);
        tLastScore.setText(String.valueOf(getChild(groupPosition, childPosition + 1)));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
