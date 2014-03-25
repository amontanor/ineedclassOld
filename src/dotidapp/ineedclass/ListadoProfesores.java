package dotidapp.ineedclass;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListadoProfesores extends Activity {

	private resultado[] profesores,auxProfes;
	private int idCategoria;
	private int idProvincia;
	private ListView listaProfesores;
	filaProfesor profe = null;
	private ArrayList<filaProfesor> list;
	private adaptadorProfesoresList adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_profesores);

		Display display = getWindowManager().getDefaultDisplay();
		int tamano = display.getHeight() / 8;

		Bundle bundle = getIntent().getExtras();

		idProvincia = bundle.getInt("idMunicipio");
		idCategoria = bundle.getInt("subcategoriaID");

		String resultJson = Herramientas
				.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=4&texto1="+ idProvincia + "&texto2=" + idCategoria);
		profesores = Herramientas.parsearListadoProfesores(resultJson);

		listaProfesores = (ListView) findViewById(R.id.lstProfesores);

		list = new ArrayList<filaProfesor>();
		for (int i = 0; i < profesores.length; ++i) {
			if (profesores[i].tipo == 1) {
				profe = new filaProfesor(getResources().getDrawable(
						R.drawable.iconop), profesores[i].cadena + " "
						+ profesores[i].cadena2, profesores[i].precio + "€/h");
				list.add(profe);
			} else if (profesores[i].tipo == 2) {
				profe = new filaProfesor(getResources().getDrawable(
						R.drawable.iconoa), profesores[i].cadena + " "
						+ profesores[i].cadena2, profesores[i].precio + "€/mes");
				list.add(profe);
			}

			// Creo el adapter personalizado
			adapter = new adaptadorProfesoresList(this, list);

			// Lo aplico
			listaProfesores.setAdapter(adapter);

		}

		listaProfesores.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = null;

				if (profesores[position].tipo == 1) {
					i = new Intent(getBaseContext(), DatosProfesor.class);
					i.putExtra("idCategoria", idCategoria);
					i.putExtra("id", "");
					i.putExtra("idProfesor", profesores[position].valor);
				}
				if (profesores[position].tipo == 2) {
					i = new Intent(getBaseContext(), DatosAcademia.class);
					i.putExtra("idCategoria", idCategoria);
					i.putExtra("id", "");
					i.putExtra("idProfesor", profesores[position].valor);
				}
				if (profesores[position].tipo == 3) {
					i = new Intent(getBaseContext(), DatosIntercambio.class);
					i.putExtra("idCategoria", idCategoria);
					i.putExtra("id", profesores[position].id);
					i.putExtra("idProfesor", profesores[position].valor);
				}

				startActivity(i);

			}
		});

		final ImageButton buttonParticulares = (ImageButton) findViewById(R.id.btnParticulares);
		buttonParticulares.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cargarDatos(1);

			}

		});

		final ImageButton buttonAcademia = (ImageButton) findViewById(R.id.btnIntercambio);
		buttonAcademia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cargarDatos(3);

			}
		});
	}

	protected void cargarDatos(int i) {
		list.clear();
		int pos =0;
		String resultJson = Herramientas
				.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=4&texto1="+ idProvincia + "&texto2=" + idCategoria);
		profesores = Herramientas.parsearListadoProfesores(resultJson);
		auxProfes = new resultado[profesores.length];
		
		for (int i1 = 0; i1 < profesores.length; ++i1) {
			if (profesores[i1].tipo == 1 && i == 1) {
				profe = new filaProfesor(getResources().getDrawable(
						R.drawable.iconop), profesores[i1].cadena + " "
						+ profesores[i1].cadena2, profesores[i1].precio + "€/h");
				list.add(profe);
				
				resultado auxResultado = new resultado();
				auxResultado.cadena=profesores[i1].cadena;
				auxResultado.cadena2=profesores[i1].cadena2;
				auxResultado.id=profesores[i1].id;
				auxResultado.precio=profesores[i1].precio;
				auxResultado.tipo=profesores[i1].tipo;
				auxResultado.valor=profesores[i1].valor;
				
				auxProfes[pos]=auxResultado;
				pos++;
			} else if (profesores[i1].tipo == 2 && i == 1) {
				profe = new filaProfesor(getResources().getDrawable(
						R.drawable.iconoa), profesores[i1].cadena + " "
						+ profesores[i1].cadena2, profesores[i1].precio
						+ "€/mes");
				list.add(profe);

				resultado auxResultado = new resultado();
				auxResultado.cadena=profesores[i1].cadena;
				auxResultado.cadena2=profesores[i1].cadena2;
				auxResultado.id=profesores[i1].id;
				auxResultado.precio=profesores[i1].precio;
				auxResultado.tipo=profesores[i1].tipo;
				auxResultado.valor=profesores[i1].valor;
				
				auxProfes[pos]=auxResultado;
				pos++;
			}

			else if (profesores[i1].tipo == 3 && i == 3) {
				profe = new filaProfesor(getResources().getDrawable(
						R.drawable.iconoi), profesores[i1].cadena + " "
						+ profesores[i1].cadena2, profesores[i1].precio);
				list.add(profe);

				resultado auxResultado = new resultado();
				auxResultado.cadena=profesores[i1].cadena;
				auxResultado.cadena2=profesores[i1].cadena2;
				auxResultado.id=profesores[i1].id;
				auxResultado.precio=profesores[i1].precio;
				auxResultado.tipo=profesores[i1].tipo;
				auxResultado.valor=profesores[i1].valor;
				
				auxProfes[pos]=auxResultado;
				pos++;
			}
			
		}
		
		profesores=auxProfes;

		listaProfesores = (ListView) findViewById(R.id.lstProfesores);
		// Creo el adapter personalizado
		adapter = new adaptadorProfesoresList(this, list);
		// Lo aplico
		listaProfesores.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listado_profesores, menu);
		return true;
	}

}
