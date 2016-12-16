package com.rabaouithamer.a4study;

/**
 * Created by Rabaoui Thamer on 07/10/2016.
 */

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class GestionnaireAppTab extends Fragment {

    private ListView lView;
    private ArrayList results = new ArrayList();
    private ArrayList resultsimage = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.application,container,false);
        lView = (ListView)v.findViewById(R.id.list1);
        PackageManager pm = getActivity().getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);
        for (ResolveInfo rInfo : list) {
            results.add(rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
            resultsimage.add(rInfo.activityInfo.applicationInfo.loadIcon(pm));




            Log.w("Installed Applications", rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
        }
        lView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, results));

        return v;
    }
}

