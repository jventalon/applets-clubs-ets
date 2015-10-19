package com.clubapplets.laboclubsets;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ListeClubs
 *
 * Activité affichant tous les clubs contenus dans la base de données.
 * Les clubs sont créés si la base de données est vide.
 *
 * @author Justine Ventalon
 * @version 1.0
 * created on 28/09/2015
 */

public class ListeClubs extends AppCompatActivity
{

    private ClubDatabaseHelper clubDatabaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clubs);

        List<Club> listeClubs;

        try
        {
            // récupère le DAO de Club
            ClubDatabaseHelper clubDbHelper = getClubDatabaseHelper();
            Dao<Club, Integer> clubDao = clubDbHelper.getDao();

            // récupère l'intent
            Intent intent = getIntent();

            // récupère tous les clubs en les créant s'ils n'existent pas encore
            listeClubs = clubDao.queryForAll();
            if (listeClubs.isEmpty())
            {
                // crée les clubs et les ajoute à la base de données
                CreateClubs(clubDao);
                // récupère la liste des clubs ajoutés à la base de données
                listeClubs = clubDao.queryForAll();
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        // construit la source de données
        ArrayList<Club> clubs = new ArrayList<>();
        clubs.addAll(listeClubs);
        // crée l'adapter pour convertir les données en vues
        ClubsAdapter adapter = new ClubsAdapter(this, clubs);
        // attache l'adapter à la vue
        ListView listView = (ListView) findViewById(R.id.liste_clubs);
        listView.setAdapter(adapter);

        // ajoute le listener pour gérer les clics sur les items de la liste
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Club club = (Club) parent.getAdapter().getItem(position);
                String website = club.getSiteweb();
                if (website != null) {
                    Uri uri = Uri.parse("http://" + website);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_liste_clubs, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
        searchView.setSearchableInfo(searchableInfo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // gestion des clics sur les éléments du menu
        int id = item.getItemId();

        if (id == R.id.action_search)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (clubDatabaseHelper != null)
        {
            OpenHelperManager.releaseHelper();
            clubDatabaseHelper = null;
        }
    }

    private ClubDatabaseHelper getClubDatabaseHelper()
    {
        if (clubDatabaseHelper == null)
        {
            clubDatabaseHelper = OpenHelperManager.getHelper(this, ClubDatabaseHelper.class);
        }
        return clubDatabaseHelper;
    }

    private void CreateClubs(Dao<Club, Integer> clubDao)
    {
        try
        {
            Club club;
            club = new Club("A.C.E.", "A-1956 et A-1742", "ic_ace", "avioncargoets.com");
            clubDao.create(club);
            club = new Club("AÉÉTS", "A-1840", "ic_aeets", "aeets.com");
            clubDao.create(club);
            club = new Club("Applets", "A-1966", "ic_applets", "applets.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Athlétsiques", "A-1199", "ic_athletsiques", "athletsiques.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Baja ÉTS", "A-1332", "ic_baja", "baja.etsmtl.ca");
            clubDao.create(club);
            club = new Club("ÉTS Bibliothèque", "Pavillon A - 1er étage", "ic_bibliotheque", "bibliotheque.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Canoë de béton", "", "ic_canoedebeton", "canoe.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Capra", "A-1746", "ic_capra", "capra.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Centre sportif", "Pavillon B - 3e étage", "ic_centresportif", "centresportif.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Chinook", "A-1748", "ic_chinook", "chinook.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Club Cycliste", "", "ic_clubcycliste", null);
            clubDao.create(club);
            club = new Club("Conjure", "A-1744", "ic_conjure", "conjure.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Coop ÉTS", "Pavillon B", "ic_coopets", "coopets.ca");
            clubDao.create(club);
            club = new Club("CRABE", "Pavillon A - S1", "ic_crabeets", "crabe.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Débat Piranha", "A-1172", "ic_debatpiranha", "debatpiranha.com");
            clubDao.create(club);
            club = new Club("DécliQ", "", "ic_decliq", "decliq.com");
            clubDao.create(club);
            club = new Club("Dronolab", "A-1760", "ic_dronolab", "dronolab.com");
            clubDao.create(club);
            club = new Club("Éclipse", "A-2519", "ic_eclipse", "eclipse.etsmtl.ca");
            clubDao.create(club);
            club = new Club("E-sporTS", "A-1950", "ic_esports", "esports.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Formule ÉTS", "A-1330", "ic_formuleets", "formuleets.ca");
            clubDao.create(club);
            club = new Club("GéniALE", "A-1244", "ic_geniale", "sites.google.com/a/etsmtl.net/geniale/home");
            clubDao.create(club);
            club = new Club("Génie Football", "", "ic_football", "geniefootball.com");
            clubDao.create(club);
            club = new Club("IEEE-ÉTS", "A-3850", "ic_ieee", "ieee.etsmtl.ca");
            clubDao.create(club);
            club = new Club("INGénieuses", "A-1172", "ic_ingenieuses", null);
            clubDao.create(club);
            club = new Club("Intégrale", "", "ic_integrale", null);
            clubDao.create(club);
            club = new Club("Lan ETS", "", "ic_lanets", "lanets.ca");
            clubDao.create(club);
            club = new Club("LiÉTS", "B-0506", "ic_liets", "liets.ca");
            clubDao.create(club);
            club = new Club("Omer", "A-1310", "ic_omer", "omerets.com");
            clubDao.create(club);
            club = new Club("Phoenix ÉTS", "", "ic_phoenix", "phoenix.etsmtl.ca");
            clubDao.create(club);
            club = new Club("PontPop ÉTS", "", "ic_pontpop", "pontpop.etsmtl.ca");
            clubDao.create(club);
            club = new Club("PRÉCI", "B-0512", "ic_preci", "preci.etsmtl.ca");
            clubDao.create(club);
            club = new Club("QuiETS", "", "ic_quiets", "teamquiets.com");
            clubDao.create(club);
            club = new Club("Radio Piranha", "A-1199", "ic_radiopiranha", "radiopiranha.com");
            clubDao.create(club);
            club = new Club("Radio Sans Génie", "A-1172", "ic_radiosansgenie", "radiosansgenie.com");
            clubDao.create(club);
            club = new Club("Rafale ÉTS", "A-3850", "ic_rafale", "etsclassc-rafale.ca");
            clubDao.create(club);
            club = new Club("ReflETS", "", "ic_reflets", null);
            clubDao.create(club);
            club = new Club("Rock & Dance", "A-1840", "ic_rockanddance", "rndets.com");
            clubDao.create(club);
            club = new Club("RockÉTS", "A-1764", "ic_rockets", "clubrockets.ca");
            clubDao.create(club);
            club = new Club("Rugby club ÉTS", "", "ic_rugby", null);
            clubDao.create(club);
            club = new Club("S.O.N.I.A.", "A-1722", "ic_sonia", "sonia.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Substance ÉTS", "", "ic_substance", "substance.etsmtl.ca");
            clubDao.create(club);
            club = new Club("Turbulence", "", "ic_turbulence", null);
            clubDao.create(club);
            club = new Club("Walking Machine", "A-1728", "ic_walkingmachine", "wm.etsmtl.ca");
            clubDao.create(club);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
