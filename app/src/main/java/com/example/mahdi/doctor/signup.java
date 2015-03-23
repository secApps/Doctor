package com.example.mahdi.doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ActionViewTarget;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class signup extends ActionBarActivity {
    private ProgressDialog pdialog;
    int sucess;
    String mssg;
    String phn_num,password,confirm_pass,name;
   // EditText Ename,Ephn_num,Epss,Econfirm_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signUp=(Button)findViewById(R.id.button2);
       // Ename=(EditText)findViewById(R.id.)
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "sign up", Toast.LENGTH_SHORT).show();
                Intent in1=new Intent(signup.this,SearchDoctor.class);
               startActivity(in1);
          //new Registration().execute();
               /* new ShowcaseView.Builder(signup.this)
                        .setTarget(new ActionViewTarget(signup.this, ActionViewTarget.Type.HOME))
                        .setContentTitle("ShowcaseView")
                        .setContentText("This is highlighting the Home button")
                        .hideOnTouchOutside()
                        .build();*/

            }
        });
    }

    public void register() {

        JSONParser jsonparser=new JSONParser();
        try{

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("mobile_no","01771083051"));
            params.add(new BasicNameValuePair("password","123456"));
            params.add(new BasicNameValuePair("status","agent"));
            params.add(new BasicNameValuePair("latitude","0"));
            params.add(new BasicNameValuePair("longitude","0"));
            params.add(new BasicNameValuePair("gcm_id","0"));
            JSONObject json = jsonparser.makeHttpRequest(
                    "http://128.199.89.223/doctor/echo_test.php", "POST", params);
            sucess = json.getInt("success");
           // mssg= json.getString("message");

        }catch(Exception e){


        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
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
    class Registration extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pdialog = new ProgressDialog(signup.this);
            pdialog.setMessage("Registering......");
            pdialog.setIndeterminate(false);
            pdialog.setCancelable(true);
            pdialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            // TODO Auto-generated method stub

            register();
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            pdialog.dismiss();
            if (sucess != 1) {
                Toast.makeText(getApplicationContext(),
                        Integer.toString(sucess), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Successfully Registered", Toast.LENGTH_SHORT).show();
               // pdialog.dismiss();
            }
        }

    }


}
