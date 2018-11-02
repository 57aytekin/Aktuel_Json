package com.aytekincomez.aktueluygulamasi.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aytekincomez.aktueluygulamasi.Adapter.ListeAdapter;
import com.aytekincomez.aktueluygulamasi.Helper.HttpHandler;
import com.aytekincomez.aktueluygulamasi.Model.Aktuel;
import com.aytekincomez.aktueluygulamasi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewTabFragmnet1 extends Fragment {
    ProgressDialog progressDialog;
    HttpHandler httpHandler;
    ArrayList<Aktuel> aktueller;
    ListView liste;

    ListeAdapter listeAdapter;
    String URL = "https://raw.githubusercontent.com/57aytekin/test_aktuelller/master/aktueller.json";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_1,container,false);


        /*
        aktueller.add(new Aktuel(1,"A101","a101","17.10.2018",""));
        aktueller.add(new Aktuel(1,"BİM","bim","17.10.2018",""));
        aktueller.add(new Aktuel(1,"ŞOK","sok","17.10.2018",""));
        aktueller.add(new Aktuel(1,"ŞOK","sok","17.10.2018",""));
*/



        liste = (ListView)view.findViewById(R.id.liste);
        new getAktueller().execute();
        //listeAdapter = new ListeAdapter(getActivity(), aktueller);




        return view;

    }

    private class getAktueller extends AsyncTask<Void, Void, Void>{
        ArrayList<Aktuel>aktueller = new ArrayList<>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Lütfen Bekleyiniz.");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listeAdapter = new ListeAdapter(getActivity(), aktueller);
            liste.setAdapter(listeAdapter);

            if(progressDialog.isShowing()){

                progressDialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            httpHandler = new HttpHandler();
            String webSiteSource = httpHandler.makeServiceCall(URL);
            Log.d("RESPONSE",webSiteSource);

            if(webSiteSource != null){
                Log.d("RESPONSE","İşlem Sürüyor..");
                try {
                    JSONObject jsonObject = new JSONObject(webSiteSource);
                    JSONArray jsonArray = jsonObject.getJSONArray("aktueller");

                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject market = jsonArray.getJSONObject(i);
                        int id = market.getInt("id");
                        String isim= market.getString("isim");
                        String marka_logo = market.getString("marka_logo");
                        String aktuel_resim = market.getString("aktuel_resim");
                        String tarih = market.getString("tarih");

                        aktueller.add(new Aktuel(
                                id, isim, marka_logo, aktuel_resim, tarih
                        ));
                    }


                }catch (JSONException e){

                }
            }else {
                Log.d("RESPONSE","Web sayfası kaynagı bulunamadı");
            }
            return null;
        }
    }
}
