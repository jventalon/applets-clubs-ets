package com.clubapplets.laboclubsets.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clubapplets.laboclubsets.R;

/**
 * ListeClubsFragment
 *
 * @author Justine Ventalon
 * @version 1.0
 * created on 28/09/2015
 */

public class ListeClubsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // layout du fragment
        return inflater.inflate(R.layout.fragment_liste_clubs, container, false);
    }
}
