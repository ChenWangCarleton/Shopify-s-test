package com.example.shopify;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ListView lst;
    SearchView sv;
    ArrayList<Product>  products=new ArrayList<>();

    ArrayList<String> tags;
    ArrayList<String> id;
    ArrayList<String> titles;
    ArrayList<String> body;
    ArrayList<String> vendor;
    ArrayList<String> types;
    ArrayList<String> created;
    ArrayList<String> handle;
    ArrayList<String> updated;
    ArrayList<String> at;
    ArrayList<String> suffix;
    ArrayList<String> scope;

    ArrayList<String> imgURL=new ArrayList<>();

    private ProgressDialog dialog;


    class getD extends AsyncTask<String,Void,ArrayList<Product>>{

        @Override
        protected ArrayList<Product> doInBackground(String... urls){
           try {
               OkHttpClient client = new OkHttpClient();
               Request request = new Request.Builder()
                       .url(urls[0])
                       .build();

               Response response = client.newCall(request).execute();
               String temp = response.body().string();
               System.out.println(temp);
               System.out.println(temp.length());
               extractData(temp);
               for (int x = 0; x < id.size(); x++) {
                    products.add(new Product(id.get(x),titles.get(x),body.get(x),vendor.get(x),types.get(x),created.get(x),handle.get(x),updated.get(x),at.get(x),suffix.get(x),scope.get(x),tags.get(x),imgURL.get(x)));
               }
               System.out.println(products.get(0).toString());

               System.out.println(products.get(49).toString());
           }catch(Exception e){
               e.printStackTrace();
           }
           return null;
        }
        @Override
        protected void onPostExecute(ArrayList<Product> result){

                sv.setQuery("",true);

        }
    }

    public void extractData(String source){

        String imgREG="\"src\":\"(.+?)\"";
        ArrayList<String> tempImg;
        tempImg= getAllMatches(source, imgREG);
        for(int x=0;x<tempImg.size();x++) {
            if(x%2==0);
            else {
                tempImg.set(x, tempImg.get(x).replace("\"src\":\"", "").replace("\"", "").replaceAll("\\/",""));
                imgURL.add(tempImg.get(x));
            }
        }

        String tagsREG="\"tags\":(.+?)\"";
        tags=getAllMatches(source,tagsREG);
        for(int x=0;x<tags.size();x++) {
            String temp=tags.get(x).replace("\"tags\":\"", "");
            tags.set(x,temp.substring(0, temp.length()-1) );
        }
        String typeREG="\"product_type\":(.+?)\"";
         types=getAllMatches(source,typeREG);
        for(int x=0;x<types.size();x++) {
            String temp=types.get(x).replace("\"product_type\":\"", "");
            types.set(x,temp.substring(0, temp.length()-1) );
        }
        String vendorREG="\"vendor\":(.+?)\"";
        vendor=getAllMatches(source,vendorREG);
        for(int x=0;x<vendor.size();x++) {
            String temp=vendor.get(x).replace("\"vendor\":\"", "");
            vendor.set(x,temp.substring(0, temp.length()-1) );
        }
        String handleREG="\"handle\":(.+?)\"";
        handle=getAllMatches(source,handleREG);
        for(int x=0;x<handle.size();x++) {
            String temp=handle.get(x).replace("\"handle\":\"", "");
            handle.set(x,temp.substring(0, temp.length()-1) );
        }
        String suffixREG="\"template_suffix\":(.+?),";
        suffix=getAllMatches(source,suffixREG);
        for(int x=0;x<suffix.size();x++) {
            String temp=suffix.get(x).replace("\"template_suffix\":", "");
            suffix.set(x,temp.substring(0, temp.length()-1) );
        }
        String atREG="\"published_at\":(.+?)\"";
        at=getAllMatches(source,atREG);
        for(int x=0;x<at.size();x++) {
            String temp = at.get(x).replace("\"published_at\":\"", "");
            at.set(x, temp.substring(0, temp.length() - 1));
        }

        String scopeREG="\"published_scope\":(.+?)\"";
        scope=getAllMatches(source,scopeREG);
        for(int x=0;x<scope.size();x++) {
            String temp=scope.get(x).replace("\"published_scope\":\"", "");
            scope.set(x,temp.substring(0, temp.length()-1) );
        }
        String bodyREG="\"body_html\":(.+?)\"";
        body=getAllMatches(source,bodyREG);
        for(int x=0;x<body.size();x++) {
            String temp=body.get(x).replace("\"body_html\":\"", "");
            body.set(x,temp.substring(0, temp.length()-1) );
        }


        String rem="\"variants\":(.+?),\"variant_ids\":";
        source=source.replaceAll(rem,"");
        String removeImage="\"image\":(.+?)variant_ids";
        source=source.replaceAll(removeImage,"");

        String titleREG="\"title\":(.+?)\"";
        titles=getAllMatches(source,titleREG);
        for(int x=0;x<titles.size();x++) {
            String temp=titles.get(x).replace("\"title\":\"", "");
            titles.set(x,temp.substring(0, temp.length()-1) );
        }
        String idREG="\"id\":(.+?),";
        id=getAllMatches(source,idREG);
        for(int x=0;x<id.size();x++) {
            String temp=id.get(x).replace("\"id\":", "");
            id.set(x,temp.substring(0, temp.length()-1) );
        }

        String createdREG="\"created_at\":(.+?)\"";
        created=getAllMatches(source,createdREG);
        for(int x=0;x<created.size();x++) {
            String temp = created.get(x).replace("\"created_at\":\"", "");
            created.set(x, temp.substring(0, temp.length() - 1));
        }
        String updatedREG="\"updated_at\":(.+?)\"";
        updated=getAllMatches(source,updatedREG);
        for(int x=0;x<updated.size();x++) {
            String temp = updated.get(x).replace("\"updated_at\":\"", "");
            updated.set(x, temp.substring(0, temp.length() - 1));
        }
    }
    public  ArrayList<String> getAllMatches(String text, String regex) {
        ArrayList<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile("(?=(" + regex + "))").matcher(text);
        while(m.find()) {
            matches.add(m.group(1));
        }
        return matches;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dialog = new ProgressDialog(getApplicationContext());


            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CustomListview customListview = new CustomListview(this,products);

        lst = (ListView) findViewById(R.id.listview);
        sv=(SearchView)findViewById(R.id.search_view);
        lst.setAdapter(customListview);
        new getD().execute("https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6");//get data from the web page

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customListview.getCustomeFilter().filter(newText);
                System.out.println("Change notified:   "+newText);
                return false;
            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(MainActivity.this,Detail.class);

                i.putExtra("title",products.get(position).getTitle());
                i.putExtra("toString",products.get(position).toString());
                i.putExtra("img",products.get(position).getImg());
                i.putExtra("position", position);

                startActivity(i);

            }
        });
    }

    }
