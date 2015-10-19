package com.clubapplets.laboclubsets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import com.clubapplets.laboclubsets.R;

/**
 * ClubDatabaseHelper
 *
 * Gère les interactions entre les objets de la classe Club et la base de données.
 *
 * @author Justine Ventalon
 * @version 1.0
 * created on 28/09/2015
 */

public class ClubDatabaseHelper extends OrmLiteSqliteOpenHelper
{
    private static final String DATABASE_NAME = "listeclubs.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Club, Integer> clubDao; // le Data Access Object utilisé pour interagir avec la base de données

    public ClubDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try
        {
            // crée la table club dans la base de données
            TableUtils.createTable(connectionSource, Club.class);

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            // recrée la table club dans la base de données
            TableUtils.dropTable(connectionSource, Club.class, false);
            onCreate(database, connectionSource);

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retourne une instance du DAO.
     *
     * @return le DAO de la classe Club
     * @throws SQLException
     */
    public Dao<Club, Integer> getDao() throws SQLException
    {
        if(clubDao == null)
        {
            clubDao = getDao(Club.class);
        }
        return clubDao;
    }

    @Override
    public void close()
    {
        super.close();
        clubDao = null;
    }
}
