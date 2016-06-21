package demirciy.ygslyspuanhesaplama.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import demirciy.ygslyspuanhesaplama.R;

/**
 * Created by Yusuf DEMİRCİ
 * Date     : 31.05.2016
 * Time     : 14:29
 * Email    : codeproject.g@gmail.com
 **/

//expandable listview in adaptörü
public class AdapterExpListView extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;

    public AdapterExpListView(Context _context, List<String> _listDataHeader, HashMap<String, List<String>> _listDataChild) {
        this._context = _context;
        this._listDataChild = _listDataChild;
        this._listDataHeader = _listDataHeader;
    }

    //grup(başlık) sayısını döndürür
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    //expandable listview in child yapısı custom olduğu için 1 değerini kullandım
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    //başlığı döndürür
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    //child ı döndürür
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    //başlığın sıra numarasını döndürür
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //child ın sırasını döndürür
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //başlıkları yazdırır
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //parent(başlık) layoutu custom olduğu için tanımlamak gerekli
            convertView = infalInflater.inflate(R.layout.exp_parent_layout, null);

        }

        TextView expExamInfo = (TextView) convertView.findViewById(R.id.expExamInfo);
        expExamInfo.setTypeface(null, Typeface.BOLD);
        expExamInfo.setText(headerTitle);

        return convertView;
    }

    //child ları yazdırır
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView expTr, expSocial, expMath1, expScience, expYgs1, expYgs2, expYgs3, expYgs4,
                expYgs5, expYgs6, expMath2, expGeo, expPhy, expChe, expBio, expLite, expGeog1,
                expHis, expGeog2, expPhi, expForeign, expMf1, expMf2, expMf3, expMf4, expTm1,
                expTm2, expTm3, expTs1, expTs2, expLang1, expLang2, expLang3;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_child_layout, null);
        }

        expTr = (TextView) convertView.findViewById(R.id.expTr);
        expSocial = (TextView) convertView.findViewById(R.id.expSocial);
        expMath1 = (TextView) convertView.findViewById(R.id.expMath1);
        expScience = (TextView) convertView.findViewById(R.id.expScience);
        expYgs1 = (TextView) convertView.findViewById(R.id.expYgs1);
        expYgs2 = (TextView) convertView.findViewById(R.id.expYgs2);
        expYgs3 = (TextView) convertView.findViewById(R.id.expYgs3);
        expYgs4 = (TextView) convertView.findViewById(R.id.expYgs4);
        expYgs5 = (TextView) convertView.findViewById(R.id.expYgs5);
        expYgs6 = (TextView) convertView.findViewById(R.id.expYgs6);
        expMath2 = (TextView) convertView.findViewById(R.id.expMath2);
        expGeo = (TextView) convertView.findViewById(R.id.expGeo);
        expPhy = (TextView) convertView.findViewById(R.id.expPhy);
        expChe = (TextView) convertView.findViewById(R.id.expChe);
        expBio = (TextView) convertView.findViewById(R.id.expBio);
        expLite = (TextView) convertView.findViewById(R.id.expLite);
        expGeog1 = (TextView) convertView.findViewById(R.id.expGeog1);
        expHis = (TextView) convertView.findViewById(R.id.expHis);
        expGeog2 = (TextView) convertView.findViewById(R.id.expGeog2);
        expPhi = (TextView) convertView.findViewById(R.id.expPhi);
        expForeign = (TextView) convertView.findViewById(R.id.expForeign);
        expMf1 = (TextView) convertView.findViewById(R.id.expMf1);
        expMf2 = (TextView) convertView.findViewById(R.id.expMf2);
        expMf3 = (TextView) convertView.findViewById(R.id.expMf3);
        expMf4 = (TextView) convertView.findViewById(R.id.expMf4);
        expTm1 = (TextView) convertView.findViewById(R.id.expTm1);
        expTm2 = (TextView) convertView.findViewById(R.id.expTm2);
        expTm3 = (TextView) convertView.findViewById(R.id.expTm3);
        expTs1 = (TextView) convertView.findViewById(R.id.expTs1);
        expTs2 = (TextView) convertView.findViewById(R.id.expTs2);
        expLang1 = (TextView) convertView.findViewById(R.id.expLang1);
        expLang2 = (TextView) convertView.findViewById(R.id.expLang2);
        expLang3 = (TextView) convertView.findViewById(R.id.expLang3);

        expTr.setText(String.valueOf("Türkçe: " + getChild(groupPosition, childPosition)));
        expSocial.setText(String.valueOf("Sosyal: " + getChild(groupPosition, childPosition + 1)));
        expMath1.setText(String.valueOf("Matematik: " + getChild(groupPosition, childPosition + 2)));
        expScience.setText(String.valueOf("Fen: " + getChild(groupPosition, childPosition + 3)));
        expYgs1.setText(String.valueOf("Ygs1: " + getChild(groupPosition, childPosition + 4)));
        expYgs2.setText(String.valueOf("Ygs2: " + getChild(groupPosition, childPosition + 5)));
        expYgs3.setText(String.valueOf("Ygs3: " + getChild(groupPosition, childPosition + 6)));
        expYgs4.setText(String.valueOf("Ygs4: " + getChild(groupPosition, childPosition + 7)));
        expYgs5.setText(String.valueOf("Ygs5: " + getChild(groupPosition, childPosition + 8)));
        expYgs6.setText(String.valueOf("Ygs6: " + getChild(groupPosition, childPosition + 9)));
        expMath2.setText(String.valueOf("Matematik2: " + getChild(groupPosition, childPosition + 10)));
        expGeo.setText(String.valueOf("Geometri: " + getChild(groupPosition, childPosition + 11)));
        expPhy.setText(String.valueOf("Fizik: " + getChild(groupPosition, childPosition + 12)));
        expChe.setText(String.valueOf("Kimya: " + getChild(groupPosition, childPosition + 13)));
        expBio.setText(String.valueOf("Biyoloji: " + getChild(groupPosition, childPosition + 14)));
        expLite.setText(String.valueOf("Edebiyat: " + getChild(groupPosition, childPosition + 15)));
        expGeog1.setText(String.valueOf("Coğrafya1: " + getChild(groupPosition, childPosition + 16)));
        expHis.setText(String.valueOf("Tarih: " + getChild(groupPosition, childPosition + 17)));
        expGeog2.setText(String.valueOf("Coğrafya2: " + getChild(groupPosition, childPosition + 18)));
        expPhi.setText(String.valueOf("Felsefe/Din: " + getChild(groupPosition, childPosition + 19)));
        expForeign.setText(String.valueOf("Yabancı Dil: " + getChild(groupPosition, childPosition + 20)));
        expMf1.setText(String.valueOf("Mf1: " + getChild(groupPosition, childPosition + 21)));
        expMf2.setText(String.valueOf("Mf2: " + getChild(groupPosition, childPosition + 22)));
        expMf3.setText(String.valueOf("Mf3: " + getChild(groupPosition, childPosition + 23)));
        expMf4.setText(String.valueOf("Mf4: " + getChild(groupPosition, childPosition + 24)));
        expTm1.setText(String.valueOf("Tm1: " + getChild(groupPosition, childPosition + 25)));
        expTm2.setText(String.valueOf("Tm2: " + getChild(groupPosition, childPosition + 26)));
        expTm3.setText(String.valueOf("Tm3: " + getChild(groupPosition, childPosition + 27)));
        expTs1.setText(String.valueOf("Ts1: " + getChild(groupPosition, childPosition + 28)));
        expTs2.setText(String.valueOf("Ts2: " + getChild(groupPosition, childPosition + 29)));
        expLang1.setText(String.valueOf("Dil1: " + getChild(groupPosition, childPosition + 30)));
        expLang2.setText(String.valueOf("Dil2: " + getChild(groupPosition, childPosition + 31)));
        expLang3.setText(String.valueOf("Dil3: " + getChild(groupPosition, childPosition + 32)));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
