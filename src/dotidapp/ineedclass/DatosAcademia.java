package dotidapp.ineedclass;

import org.json.JSONArray;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DatosAcademia extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datos_academia);
		
		
		Bundle bundle = getIntent().getExtras();
        int idProfesor = bundle.getInt("idProfesor");
        int idCategoria = bundle.getInt("idCategoria");
        
        String resultJson= Herramientas.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=5&texto1="+idProfesor+"&texto2="+idCategoria);
    	Profesor profesor = Herramientas.parsearProfesor(resultJson);
    	
    	TextView txtNombreAcademia = (TextView)findViewById(R.id.txtNombreAcademia);
    	TextView txtContacto = (TextView)findViewById(R.id.txtContactoAcademia);
    	TextView txtPrecio = (TextView)findViewById(R.id.txtPrecioAcademia);
    	TextView txtComentarios = (TextView)findViewById(R.id.txtComentariosAcademia);
    	
    	txtNombreAcademia.setText(profesor.nombre.replace("%20", " ").replace("%30", "ñ"));
    	txtContacto.setText(profesor.mail.replace("%20", " ").replace("%30", "ñ")+" - " +profesor.telefono);
    	txtPrecio.setText(profesor.precio.replace("%20", " ")+" €/mes");
    	txtComentarios.setText(profesor.comentarios.replace("%20", " "));
    	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.datos_academia, menu);
		return true;
	}

}
