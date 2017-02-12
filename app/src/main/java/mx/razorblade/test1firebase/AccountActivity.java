package mx.razorblade.test1firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountActivity extends AppCompatActivity {
    Button botonP;
    FirebaseAuth firebaseAuth;
    TextView textoUsu;
    EditText Titulo;
    EditText Parrafo;
    String nombre;
    DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        firebaseAuth = FirebaseAuth.getInstance();
        textoUsu = (TextView)findViewById(R.id.textoUsuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        botonP = (Button)findViewById(R.id.botonPublicar);
        Titulo = (EditText)findViewById(R.id.Titulo);
        Parrafo =(EditText)findViewById(R.id.parrafo);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref =database.getReference("post");
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = database.getReference("users");
        nombre= user.getUid();;
        Log.e("dato del hijo",databaseReference.child(nombre).child("ciudad").getKey());
        if (databaseReference.child(nombre) == null){
            //databaseReference.child(nombre).child("Ciudad").setValue("no Registrada");
            Intent intent = new Intent(getApplicationContext(),WallPosts.class);
            startActivityForResult(intent,1);
        }
                textoUsu.setText(user.getEmail());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
              //  Usuario usuario = new Usuario("titulo","parrafo",null);
                //ref.child(user.getUid()).setValue(usuario);
                Intent intent = new Intent(AccountActivity.this,WallPosts.class);
                startActivity(intent);
            }
        });

        botonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario(Titulo.getText().toString(),Parrafo.getText().toString(),user.getUid());
                ref.push().child(user.getUid()).setValue(usuario);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_settings:
                    Intent intent = new Intent(this, WallPosts.class);
                    this.startActivity(intent);
                    break;
                default:
                    return super.onOptionsItemSelected(item);
            }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK){
            databaseReference.child(nombre).child("Ciudad").setValue("no");
        }
    }
}
