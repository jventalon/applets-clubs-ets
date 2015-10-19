package com.clubapplets.laboclubsets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ClubsAdapter
 *
 * Lien entre la vue fragment_liste_clubs et les données utilisées pour remplir cette vue.
 *
 * @author Justine Ventalon
 * @version 1.0
 * created on 28/09/2015
 */

public class ClubsAdapter extends ArrayAdapter<Club>
{
    public ClubsAdapter(Context context, ArrayList<Club> clubs)
    {
        super(context, 0, clubs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // récupère le club à la position passée en paramètre
        Club club = getItem(position);

        // si la vue n'est pas déjà utilisée
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_liste_clubs, parent, false);
        }

        // récupère les vues à remplir
        ImageView viewIcone = (ImageView) convertView.findViewById(R.id.icone);
        TextView viewNom = (TextView) convertView.findViewById(R.id.nom);
        TextView viewLocal = (TextView) convertView.findViewById(R.id.local);

        // remplit les vues avec les données
        int id = this.getContext().getResources().getIdentifier(club.getIcone(), "drawable", this.getContext().getPackageName());
        viewIcone.setImageResource(id);
        viewNom.setText(club.getNom());
        viewLocal.setText(club.getLocal());

        // retourne la vue remplie qui sera affichée sur l'écran
        return convertView;
    }
}
