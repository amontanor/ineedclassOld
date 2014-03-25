package dotidapp.ineedclass;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DatosIntercambio extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datos_intercambio);

		Bundle bundle = getIntent().getExtras();
		int idProfesor = bundle.getInt("idProfesor");
		int idCategoria = bundle.getInt("idCategoria");
		String id = bundle.getString("id");

		String resultJson = Herramientas
				.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=8&texto1="
						+ idProfesor + "&texto2=" + id);
		Profesor profesor = Herramientas.parsearProfesorIntercambio(resultJson);

		TextView busco = (TextView) findViewById(R.id.txtBusco);
		TextView ofrezco = (TextView) findViewById(R.id.txtOfrezco);
		TextView contacto = (TextView) findViewById(R.id.txtContactoIntercambio);
		TextView comentarios = (TextView) findViewById(R.id.txtComentariosIntercambio);

		try {
			// Busco texto / Ofrezco texto
			resultJson = Herramientas
					.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=9&texto="
							+ profesor.busco);
			JSONArray jArray = new JSONArray(resultJson);
			String auxBusco = jArray.getJSONObject(0).getString("nombre");

			resultJson = Herramientas
					.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=9&texto="
							+ profesor.ofrezco);
			jArray = new JSONArray(resultJson);
			String auxOfrezco = jArray.getJSONObject(0).getString("nombre");
			// FIN

			busco.setText(auxBusco.replace("%20", " ").replace("%30", "ñ"));
			ofrezco.setText(auxOfrezco.replace("%20", " ").replace("%30", "ñ"));
			contacto.setText(profesor.mail.replace("%20", " ").replace("%30", "ñ") +" - " + profesor.telefono);
			comentarios.setText(profesor.comentarios.replace("%20", " "));
		} catch (Exception e) {

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.datos_intercambio, menu);
		return true;
	}

}
