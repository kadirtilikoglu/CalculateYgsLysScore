package demirciy.ygslyspuanhesaplama;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpListViewAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem, tempChild;
    public ArrayList<Object> Childtem = new ArrayList<>();
    public LayoutInflater minflater;
    public Activity activity;

    public ExpListViewAdapter(ArrayList<String> groupItem, ArrayList<Object> childtem) {
        this.groupItem = groupItem;
        this.Childtem = childtem;
    }

    public void setInflater(LayoutInflater minflater, Activity activity) {
        this.minflater = minflater;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Childtem.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.exp_parent_layout, null);
        }

        ((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        tempChild = (ArrayList<String>) Childtem.get(groupPosition);

        //tempChild = (ArrayList<String>) Childtem.get(groupPosition);

        TextView expTr, expSocial, expMath1, expScience, expYgs1, expYgs2, expYgs3, expYgs4,
                expYgs5, expYgs6, expMath2, expGeo, expPhy, expChe, expBio, expLite, expGeog1,
                expHis, expGeog2, expPhi, expForeign, expMf1, expMf2, expMf3, expMf4, expTm1,
                expTm2, expTm3, expTs1, expTs2, expLang1, expLang2, expLang3;

        if (convertView == null) {
            convertView = minflater.inflate(R.layout.exp_child_layout, null);
        }

        expTr = (TextView) convertView.findViewById(R.id.expTr);
        expTr.setText(tempChild.get(0));
        expSocial = (TextView) convertView.findViewById(R.id.expSocial);
        expSocial.setText(tempChild.get(1));
        expMath1 = (TextView) convertView.findViewById(R.id.expMath1);
        expMath1.setText(tempChild.get(2));
        expScience = (TextView) convertView.findViewById(R.id.expScience);
        expScience.setText(tempChild.get(3));
        expYgs1 = (TextView) convertView.findViewById(R.id.expYgs1);
        expYgs1.setText(tempChild.get(4));
        expYgs2 = (TextView) convertView.findViewById(R.id.expYgs2);
        expYgs2.setText(tempChild.get(5));
        expYgs3 = (TextView) convertView.findViewById(R.id.expYgs3);
        expYgs3.setText(tempChild.get(6));
        expYgs4 = (TextView) convertView.findViewById(R.id.expYgs4);
        expYgs4.setText(tempChild.get(7));
        expYgs5 = (TextView) convertView.findViewById(R.id.expYgs5);
        expYgs5.setText(tempChild.get(8));
        expYgs6 = (TextView) convertView.findViewById(R.id.expYgs6);
        expYgs6.setText(tempChild.get(9));
        expMath2 = (TextView) convertView.findViewById(R.id.expMath2);
        expMath2.setText(tempChild.get(10));
        expGeo = (TextView) convertView.findViewById(R.id.expGeo);
        expGeo.setText(tempChild.get(11));
        expPhy = (TextView) convertView.findViewById(R.id.expPhy);
        expPhy.setText(tempChild.get(12));
        expChe = (TextView) convertView.findViewById(R.id.expChe);
        expChe.setText(tempChild.get(13));
        expBio = (TextView) convertView.findViewById(R.id.expBio);
        expBio.setText(tempChild.get(14));
        expLite = (TextView) convertView.findViewById(R.id.expLite);
        expLite.setText(tempChild.get(15));
        expGeog1 = (TextView) convertView.findViewById(R.id.expGeog1);
        expGeog1.setText(tempChild.get(16));
        expHis = (TextView) convertView.findViewById(R.id.expHis);
        expHis.setText(tempChild.get(17));
        expGeog2 = (TextView) convertView.findViewById(R.id.expGeog2);
        expGeog2.setText(tempChild.get(18));
        expPhi = (TextView) convertView.findViewById(R.id.expPhi);
        expPhi.setText(tempChild.get(19));
        expForeign = (TextView) convertView.findViewById(R.id.expForeign);
        expForeign.setText(tempChild.get(20));
        expMf1 = (TextView) convertView.findViewById(R.id.expMf1);
        expMf1.setText(tempChild.get(21));
        expMf2 = (TextView) convertView.findViewById(R.id.expMf2);
        expMf2.setText(tempChild.get(22));
        expMf3 = (TextView) convertView.findViewById(R.id.expMf3);
        expMf3.setText(tempChild.get(23));
        expMf4 = (TextView) convertView.findViewById(R.id.expMf4);
        expMf4.setText(tempChild.get(24));
        expTm1 = (TextView) convertView.findViewById(R.id.expTm1);
        expTm1.setText(tempChild.get(25));
        expTm2 = (TextView) convertView.findViewById(R.id.expTm2);
        expTm2.setText(tempChild.get(26));
        expTm3 = (TextView) convertView.findViewById(R.id.expTm3);
        expTm3.setText(tempChild.get(27));
        expTs1 = (TextView) convertView.findViewById(R.id.expTs1);
        expTs1.setText(tempChild.get(28));
        expTs2 = (TextView) convertView.findViewById(R.id.expTs2);
        expTs2.setText(tempChild.get(29));
        expLang1 = (TextView) convertView.findViewById(R.id.expLang1);
        expLang1.setText(tempChild.get(30));
        expLang2 = (TextView) convertView.findViewById(R.id.expLang2);
        expLang2.setText(tempChild.get(31));
        expLang3 = (TextView) convertView.findViewById(R.id.expLang3);
        expLang3.setText(tempChild.get(32));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
}
