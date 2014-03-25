package dotidapp.ineedclass;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DatosProfesor extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_datos_profesor);
		
		Bundle bundle = getIntent().getExtras();
        int idProfesor = bundle.getInt("idProfesor");
        int idCategoria = bundle.getInt("idCategoria");
        
        String resultJson= Herramientas.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=5&texto1="+idProfesor+"&texto2="+idCategoria);
    	Profesor profesor = Herramientas.parsearProfesor(resultJson);
    	
    	TextView txtNombre = (TextView)findViewById(R.id.txtNombre);
    	TextView txtTitulacion = (TextView)findViewById(R.id.txtTitulacion);
    	TextView txtNivel = (TextView)findViewById(R.id.txtNivel);
    	TextView txtContacto = (TextView)findViewById(R.id.txtContacto);
    	TextView txtPrecio = (TextView)findViewById(R.id.txtPrecio);
    	TextView txtTelefono = (TextView)findViewById(R.id.txtTlf);
    	TextView txtComentario = (TextView)findViewById(R.id.txtComentarios);
    	
    	txtNombre.setText(profesor.nombre.replace("%20", " ")+" "+profesor.apellido.replace("%20", " "));
    	txtTitulacion.setText(profesor.titulacion.replace("%20", " "));
    	txtNivel.setText(profesor.nivel.replace("%20", " "));
    	txtTelefono.setText(profesor.telefono.replace("%20", " "));
    	txtPrecio.setText(profesor.precio.replace("%20", " ")+"€/h");
    	txtComentario.setText(profesor.comentarios.replace("%20", " "));
    	txtContacto.setText(profesor.mail.replace("%20", " "));
    	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.datos_profesor, menu);
		return true;
	}

}
