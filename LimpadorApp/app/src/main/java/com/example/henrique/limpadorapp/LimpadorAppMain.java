package com.example.henrique.limpadorapp;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class LimpadorAppMain extends ActionBarActivity {

    private static final String ARQUIVO = "arquivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limpador_app_main);
        Button Borra = (Button) findViewById(R.id.buttona);
        //salvar();
        Borra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletar();
                Log.i("ok", "ok");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_limpador_app_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void salvar() {
        try {
            File f2 = getApplicationContext().getFilesDir();
            //File f3[] = getApplicationContext().getExternalFilesDirs();
            File f = SDCardUtils.getSDCardFile("livroandroid", ARQUIVO);

            File dir = new File(f2, "pastateste");
            if(!dir.exists()) {
                dir.mkdir();
            }

            File fs = new File(dir, ARQUIVO);
            FileOutputStream out = new FileOutputStream(fs);
            String msg = "ajksdhalhdlaskh";
            out.write(msg.getBytes());
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void deletar () {
        String[] dirs = {"WhatsApp Images", "WhatsApp Video", "WhatsApp Voice Notes", "WhatsApp Audio"};
        File f = android.os.Environment.getExternalStorageDirectory();
        f = new File(f, "WhatsApp");
        f = new File(f, "Media");

        if(f.exists()) {

            for (int i = 0; i < dirs.length; i++) {
                File f3 = new File(f, dirs[i]);
                try {
                    FileUtils.cleanDirectory(f3);
                } catch (IOException e) {
                    Log.e("Erro", "Não apagou " + dirs[i]);
                }
            }
            Toast toast = Toast.makeText(this, "Arquivos do WhatsApp deletados.", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
        else {
            Toast toast = Toast.makeText(this, "WhastsApp Não encontrado.", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }

    }

    private void eraseFiles(File[] files) {
        for (File f: files) {
            if (f.isDirectory())
                eraseFiles(f.listFiles());
            else
                f.delete();
        }

    }
}
