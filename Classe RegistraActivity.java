package com.example.compartilhamentodearquivo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistraActivity extends AppCompatActivity {

    EditText et_username, et_pass1, et_pass2;
    Button bt_registrar;
    Conexao db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra);

        db = new Conexao(this);

        et_username = (EditText)findViewById(R.id.et_reg_username);
        et_pass1 = (EditText)findViewById(R.id.et_reg_password1);
        et_pass2 = (EditText)findViewById(R.id.et_reg_password2);

        bt_registrar = (Button)findViewById(R.id.bt_registrarnovo);

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String p1 = et_pass1.getText().toString();
                String p2 = et_pass2.getText().toString(); // 2

                if (username.equals("")){
                    Toast.makeText(RegistraActivity.this, "Username não iserido, tente novamento", Toast.LENGTH_SHORT).show();
                }else if (p1.equals("")|| p2.equals("")){
                    Toast.makeText(RegistraActivity.this, "Preencha o password corretamente, tente novamento", Toast.LENGTH_SHORT).show();
                } else if (!p1.equals(p2)){
                    Toast.makeText(RegistraActivity.this, "Os passwords não correspodem, tente novamento", Toast.LENGTH_SHORT).show();
                } else{
                    long res = db.CriarUtilizador(username, p1);
                    if(res>0){
                        Toast.makeText(RegistraActivity.this,"Registro Ok", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistraActivity.this, "Registro inválido, tente novamento", Toast.LENGTH_SHORT).show();
                    }    }
            }
        });



    }
}
