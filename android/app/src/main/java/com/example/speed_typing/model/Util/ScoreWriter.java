package com.example.speed_typing.model.Util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.speed_typing.model.Scores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Random;

public class ScoreWriter {

    public static void write(Context context, List<Scores> scores){
        String ligne[];

        int nbWordWrite;
        int nbWordFailed;
        float nbCharactere;
        String name;
        String photoPath;
        int time;

        try
        {
            String fileName = "Scores.txt";
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);

               for (int i = 0; i<scores.size(); i++){
                   name = scores.get(i).name();
                   time = scores.get(i).time();
                   nbWordWrite = scores.get(i).getNbWordWrite();
                   nbWordFailed = scores.get(i).getNbWordFailed();
                   nbCharactere = scores.get(i).getNbCaracterePerSec();
                   photoPath = scores.get(i).getPhotoPath();
                   writer.write(name+"_"+time+"_"+nbWordWrite+"_"+nbWordFailed+"_"+nbCharactere+"_"+photoPath);

                }
                writer.close();
               fos.close();


        } catch (IOException e) {
            Log.d(e.getMessage(),"IO exeption dans ScoreWriter");

        }
    }

    // Ecrit une image dans un fichier binaire
    public static String writeImage(Bitmap img, Context context) {
        String pathName = generateRandomPath();

        // Création du fichier
        File f = new File(context.getFilesDir() + "/" + pathName);

        // Ouverture du fichier
        // et enregistrement en binaire
        try {
            FileOutputStream ops = context.openFileOutput(pathName, Context.MODE_PRIVATE);
            img.compress(Bitmap.CompressFormat.PNG, 100, ops);
        } catch (FileNotFoundException e) {
            Log.d("writeImage","Création d'un FileOutput stream impossible");
        }


        return pathName;
    }

    public static String generateRandomPath() {
        Random r = new Random();

        int length = 16;
        char lettres[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String str = "";

        for (int i = 0; i < length; i++) {
            str += lettres[r.nextInt(lettres.length)];
        }

            return str;

    }
}
