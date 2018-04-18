package com.fablabburgos.fablabburgos.vista.socio;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.controlador.Formato;
import com.fablabburgos.fablabburgos.controlador.Password;
import com.fablabburgos.fablabburgos.db.socio.SocioInsert;
import com.fablabburgos.fablabburgos.vista.dataPiker.DatePickerFragment;

import org.apache.commons.validator.routines.EmailValidator;

import static com.fablabburgos.fablabburgos.db.Conexion.INSERT_SOCIO;

public class SocioRegistroActivity extends AppCompatActivity {

    EditText txtUser, txtMail, txtPass, txtNombre, txtTelefono;
    Button btnRegistrar,btnFechaNac;
    SocioInsert socio_insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio_registro);

        txtUser = findViewById(R.id.txtUserReg);
        txtMail = findViewById(R.id.txtEmailLog);
        txtPass = findViewById(R.id.txtPassLog);
        txtNombre = findViewById(R.id.txtNombreReg);
        txtTelefono = findViewById(R.id.txtTelefonoReg);
        btnFechaNac = findViewById(R.id.btnFechaNacReg);

        btnRegistrar = findViewById(R.id.btnRegistroReg);


        txtMail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(EmailValidator.getInstance().isValid(txtMail.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Email valido",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtPass.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // [ON CLICK LISTENER]
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        String user = txtUser.getText().toString();
        String pass = txtPass.getText().toString();
        String nombre = txtNombre.getText().toString();
        String mail = txtMail.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String fecha = Formato.fechaUS(btnFechaNac.getText().toString());

                //[VALIDACION DE CAMPOS]
        if (!user.equals("") && user.length()>0 && user.length()<=45){
            if (!mail.equals("") && mail.length()>0 && mail.length()<=90 && EmailValidator.getInstance().isValid(mail)){
                if (!pass.equals("") && pass.length()>0 && pass.length()<=20){
                    if (!nombre.equals("") && nombre.length()>0 && nombre.length()<=150){
                        if (telefono.length()==9){

                             socio_insert = new SocioInsert(SocioRegistroActivity.this,getApplicationContext());
                             socio_insert.execute(INSERT_SOCIO,
                                                        user,
                                                        Password.getPasswordSeguro(pass),
                                                        nombre,
                                                        mail,
                                                        telefono,
                                                        fecha);   // Parámetros que recibe doInBackground

                        }else{toast("Error en el campo telefono");}
                    }else{toast("Error en el campo nombre y apellidos");}
                }else{toast("Error en el campo contraseña");}
            }else{toast("Error en el campo email");}
        }else{toast("Error en el campo Apodo");}

            }
        }); // FIN ON CLICK
    } // FIN ON CREATE

    // [WARNING TOAST]
    private void toast(String texto){
        Toast.makeText(getApplicationContext(),
            texto, Toast.LENGTH_SHORT).show();
    }

    // [DATA PICKER]
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}// FIN ACTIVITY





