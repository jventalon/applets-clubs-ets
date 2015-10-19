package com.clubapplets.laboclubsets;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Club
 *
 * Représente un Club étudiant de l'École de Technologie Supérieure.
 *
 * @author Justine Ventalon
 * @version 1.0
 * created on 28/09/2015
 */

@DatabaseTable
public class Club
{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String nom;

    @DatabaseField
    private String local;

    @DatabaseField
    private String icone;

    @DatabaseField
    private String siteweb;

    public Club()
    {

    }

    public Club(String nom, String local, String icone, String siteweb)
    {
        this.nom = nom;
        this.local = local;
        this.icone = icone;
        this.siteweb = siteweb;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getLocal()
    {
        return local;
    }

    public void setLocal(String local)
    {
        this.local = local;
    }

    public String getIcone()
    {
        return icone;
    }

    public void setIcone(String icone)
    {
        this.icone = icone;
    }

    public String getSiteweb()
    {
        return siteweb;
    }

    public void setSiteweb(String siteweb)
    {
        this.siteweb = siteweb;
    }

    public String toString()
    {
        return "ID : " + id + "\nNom : " + nom + "\nLocal : " + local + "\nIcone : " + icone
                + "\nSiteweb : " + siteweb;
    }
}
