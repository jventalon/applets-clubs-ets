package com.clubapplets.laboclubsets;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
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
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ListeClubsSearch
 *
 * Activité affichant tous les clubs contenus dans la base de données correspondant à la requête.
 *
 * @author Justine Ventalon
 * @version 1.0
 * created on 28/09/2015
 */

public class ListeClubsSearch extends AppCompatActivity
{

    private ClubDatabaseHelper clubDatabaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_clubs);

        List<Club> listeClubs = new ArrayList<>();

        try
        {
            // récupère le DAO de Club
            ClubDatabaseHelper clubDbHelper = getClubDatabaseHelper();
            Dao<Club, Integer> clubDao = clubDbHelper.getDao();

            // récupère l'intent
            Intent intent = getIntent();
            // exécute la requête et récupère les clubs correspondants
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                String query = intent.getStringExtra(SearchManager.QUERY);
                // construit la requête
                QueryBuilder<Club, Integer> queryBuilder = clubDao.queryBuilder();
                Where<Club, Integer> where = queryBuilder.where();
                where.like("nom", query + "%");
                // prépare la requête
                PreparedQuery<Club> preparedQuery = queryBuilder.prepare();
                // exécute la requête
                listeClubs = clubDao.query(preparedQuery);
                if (listeClubs.isEmpty())
                {
                    //TODO : display not found message
                }
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
        listView.setEmptyView(findViewById(R.id.liste_vide));
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
        ComponentName cn = new ComponentName(this, ListeClubsSearch.class);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(cn);
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
        if (id == R.id.home)
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
}
