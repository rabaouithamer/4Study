package com.rabaouithamer.a4study;

/**
 * Created by Rabaoui Thamer on 07/10/2016.
 */


import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ListView;







public class ToDoTab extends Fragment{
    private Context context ;



    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.to_do_list,container,false);

        DatabBaseHandler base = new DatabBaseHandler(v.getContext(), null, null, 1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, base.extraireDonne());

        list=(ListView)v.findViewById(R.id.listeToDo);
        list.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_cc, null);
                dialogBuilder.setView(dialogView);

                final EditText nom = (EditText) dialogView.findViewById(R.id.textnom);
                final EditText contenu = (EditText) dialogView.findViewById(R.id.textcontenu);

                dialogBuilder.setTitle("Ajout de Notes");
                dialogBuilder.setMessage("Saisir les coordonnées");
                dialogBuilder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {



                        Notes contact = new Notes(nom.getText().toString(),contenu.getText().toString());
                        // contact.set_id(curseur);
                        //BASE.addNotes(contact);

                        Intent i = new Intent(getActivity(), ToDoTab.class);  //your class
                        startActivity(i);





                    }
                });
                dialogBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //mana3emlou chay
                        //just el alertDialog tetna77a w barra
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();

            }
        });


        return v;
    }





}

