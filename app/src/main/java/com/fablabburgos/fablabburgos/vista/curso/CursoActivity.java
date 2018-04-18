package com.fablabburgos.fablabburgos.vista.curso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fablabburgos.fablabburgos.R;
import com.fablabburgos.fablabburgos.modelo.Curso;

public class CursoActivity extends AppCompatActivity  implements FragmentoCurso.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        if (findViewById(R.id.fragmento_contenedor_cur) != null && savedInstanceState == null){
            FragmentoCurso fragmento_curso = new FragmentoCurso();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_contenedor_cur,fragmento_curso).commit();
        }else{
            FragmentoCurso fragmento_curso = new FragmentoCurso();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_curso,fragmento_curso).commit();

            FragmentoCursoDetalle fragmento_curso_detalle = new FragmentoCursoDetalle();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_curso_detalle,fragmento_curso_detalle).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Curso curso) {
        FragmentoCursoDetalle fragmentoCursoDetalle = (FragmentoCursoDetalle) getSupportFragmentManager().findFragmentById(R.id.fragmento_curso_detalle);
        if (fragmentoCursoDetalle != null){
            //fragmentoCursoDetalle.cambio(curso);
        }else{
            fragmentoCursoDetalle = new FragmentoCursoDetalle();
            Bundle args = new Bundle();
            args.putParcelable("curso", curso);
            fragmentoCursoDetalle.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmento_contenedor_cur,fragmentoCursoDetalle)
                    .addToBackStack(null).commit();

        }
    }
}
