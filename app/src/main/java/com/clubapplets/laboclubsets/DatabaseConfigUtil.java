package com.clubapplets.laboclubsets;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * DatabaseConfigUtil
 *
 * Génère le fichier de configuration ormlite_config.txt dans le dossier de ressources res/raw/.
 *
 * @author Justine Ventalon
 * @version 1.0
 * created on 28/09/2015
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil
{
    private static final Class<?>[] classes = new Class[] {Club.class};

    public static void main(String[] args) throws SQLException, IOException
    {
        String currDirectory = "user.dir";

        String configPath = "/app/src/main/res/raw/ormlite_config.txt";

        // récupère la racine du projet
        String projectRoot = System.getProperty(currDirectory);

        // crée le chemin de configuration complet
        String fullConfigPath = projectRoot + configPath;

        File configFile = new File(fullConfigPath);

        // recrée le fichier de configuration
        if(configFile.exists())
        {
            configFile.delete();
            configFile = new File(fullConfigPath);
        }

        // écrit le contenu du fichier de configuration
        writeConfigFile(configFile, classes);
    }
}
