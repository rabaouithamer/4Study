package com.rabaouithamer.a4study;


public class Notes {

    private int id ;

    private String nom ;
    private String contenu ;


    public Notes ( String nom , String contenu){
        this.contenu=contenu ;
        this.nom=nom ;

    }

    public void set_id(int id){
        this.id = id;
    }

    public void set_nom(String nom){
        this.nom = nom;
    }



    public void set_contenu(String contenu){
        this.contenu = contenu;
    }

    public String get_contenu(){
        return contenu ;

    }

    public String get_nom(){
        return nom ;

    }




    public int get_id(){
        return id;

    }

}
