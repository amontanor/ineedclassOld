package dotidapp.ineedclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

public class NuevoBuscador extends Activity implements OnClickListener {

	componenteSeleccion c1, c2, c3, c4;
	String provincia, municipio, categoria, subcategoria;
	int provinciaId = -1, municipioId = -1, categoriaId = -1,
			subcategoriaId = -1;
	private String resultJson;
	private int bloqueo;
	private ProgressDialog dialog;
	private int pulsado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_buscador);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Display display = getWindowManager().getDefaultDisplay();
		int tamano = display.getHeight() / 20;

		c1 = (componenteSeleccion) findViewById(R.id.componente1);
		c2 = (componenteSeleccion) findViewById(R.id.componente2);
		c3 = (componenteSeleccion) findViewById(R.id.componente3);
		c4 = (componenteSeleccion) findViewById(R.id.componente4);

		c1.initUI("Seleccione Provincia", "", 1, tamano, 1);
		c2.initUI("Seleccione Municipio", "", 1, tamano, 2);
		c3.initUI("Seleccione Tipo de Materia", "", 1, tamano, 3);
		c4.initUI("Seleccione Materia", "", 1, tamano, 4);
		// c1.imagen = (ImageView) this.findViewById(R.id.icon);

		this.c1.setOnClickListener(NuevoBuscador.this);
		this.c2.setOnClickListener(this);
		this.c3.setOnClickListener(this);
		this.c4.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		ejecutarJson ejecutador = new ejecutarJson(NuevoBuscador.this);
		switch (v.getId()) {
		case 1:
			pulsado = 1;
			ejecutador
					.execute(
							"http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=1",
							"a");

			c1.seleccionaX.setTextColor(Color.WHITE);
			categoriaId = -1;
			municipioId = -1;
			break;
		case 2:
			pulsado = 2;
			if (provinciaId != -1) {
				ejecutador
						.execute("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=2&texto="
								+ String.valueOf(provinciaId));

				categoriaId = -1;
				municipioId = -1;
			} else
				Toast.makeText(getBaseContext(),
						"Debe seleccionar una provincia antes!",
						Toast.LENGTH_LONG).show();
			break;
		case 3:
			pulsado = 3;
			if (municipioId != -1) {
				ejecutador
						.execute("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=6&texto="
								+ String.valueOf(municipioId));
				categoriaId = -1;
			} else
				Toast.makeText(getBaseContext(),
						"Debe seleccionar un municipio antes!",
						Toast.LENGTH_LONG).show();
			break;
		case 4:
			pulsado = 4;
			if (categoriaId != -1) {
				ejecutador
						.execute("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=3&texto1="
								+ String.valueOf(municipioId)
								+ "&texto2="
								+ String.valueOf(categoriaId));
				c4.seleccionaX.setTextColor(Color.WHITE);
			} else
				Toast.makeText(getBaseContext(),
						"Debe seleccionar una categoría antes!",
						Toast.LENGTH_LONG).show();
			break;
		}
	}


	private class ejecutarJson extends AsyncTask<String, Void, Void> {

		private Context context;

		public ejecutarJson(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(NuevoBuscador.this);
			dialog.setMessage("Cargando...");
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.show();
		}

		/**
		 * The system calls this to perform work in a worker thread and delivers
		 * it the parameters given to AsyncTask.execute()
		 */

		protected Void doInBackground(String... urls) {

			resultJson = Herramientas.jsonLoad(urls[0]);
			bloqueo = 1;
			return null;
		}

		protected void onPostExecute(Void result) {
			dialog.dismiss();
			if (pulsado == 1) {
				while (bloqueo == 0)
					;
				final resultado[] ciudades = Herramientas.parsear(resultJson);

				final String[] nombreCiudades = new String[ciudades.length];

				for (int i = 0; i < ciudades.length; i++) {
					nombreCiudades[i] = new String(ciudades[i].cadena);
				}

				AlertDialog.Builder builder = new AlertDialog.Builder(
						NuevoBuscador.this);
				builder.setTitle("Selecciona Provincia");
				builder.setItems(nombreCiudades,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								provincia = ciudades[which].cadena;
								provinciaId = ciudades[which].valor;
								c1.resultadoX.setText(provincia.replace("%20",
										" "));
								c1.resultadoX.setText(provincia.replace("%30",
										"ñ"));
								c1.imagen.setImageResource(R.drawable.ok);
								c1.setBackgroundResource(R.drawable.fondoincverde);
								c1.seleccionaX.setTextColor(Color.DKGRAY);

								c2.imagen.setImageResource(R.drawable.no);
								c2.setBackgroundResource(R.drawable.grey_wash_wall_2);
								c2.resultadoX.setText("");
								c2.seleccionaX.setTextColor(Color.WHITE);

								c3.imagen.setImageResource(R.drawable.no);
								c3.setBackgroundResource(R.drawable.grey_wash_wall_2);
								c3.resultadoX.setText("");
								c3.seleccionaX.setTextColor(Color.WHITE);

								c4.imagen.setImageResource(R.drawable.no);
								c4.setBackgroundResource(R.drawable.grey_wash_wall_2);
								c4.resultadoX.setText("");
								c4.seleccionaX.setTextColor(Color.WHITE);
							}
						});
				builder.show();
			}

			if (pulsado == 2) {
				while (bloqueo == 0)
					;
				final resultado[] provincias = Herramientas
						.parsearProvincia(resultJson);
				final String[] nombreProvincias = new String[provincias.length];

				for (int i = 0; i < provincias.length; i++) {
					nombreProvincias[i] = new String(provincias[i].cadena);
				}

				AlertDialog.Builder builder = new AlertDialog.Builder(
						NuevoBuscador.this);
				builder.setTitle("Selecciona Municipio");
				builder.setItems(nombreProvincias,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								municipio = provincias[which].cadena;
								municipioId = provincias[which].valor;
								c2.resultadoX.setText(provincia.replace("%20", " "));
								c2.resultadoX.setText(provincia.replace("%30", "ñ"));

								c2.imagen.setImageResource(R.drawable.ok);
								c2.setBackgroundResource(R.drawable.fondoincverde);
								c2.seleccionaX.setTextColor(Color.DKGRAY);

								c3.imagen.setImageResource(R.drawable.no);
								c3.setBackgroundResource(R.drawable.grey_wash_wall_2);
								c3.resultadoX.setText("");
								c3.seleccionaX.setTextColor(Color.WHITE);

								c4.imagen.setImageResource(R.drawable.no);
								c4.setBackgroundResource(R.drawable.grey_wash_wall_2);
								c4.resultadoX.setText("");
								c4.seleccionaX.setTextColor(Color.WHITE);

							}
						});
				builder.show();

			}

			if (pulsado == 3) {
				while (bloqueo == 0)
					;
				final resultado[] categorias = Herramientas
						.parsearCategoria(resultJson);
				final String[] nombreCategoria = new String[categorias.length];

				for (int i = 0; i < categorias.length; i++) {
					nombreCategoria[i] = new String(categorias[i].cadena);
				}

				AlertDialog.Builder builder = new AlertDialog.Builder(
						NuevoBuscador.this);
				builder.setTitle("Selecciona Categoría");
				builder.setItems(nombreCategoria,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								categoria = categorias[which].cadena;
								categoriaId = categorias[which].valor;
								c3.resultadoX.setText(categoria.replace("%20",
										" "));
								c3.resultadoX.setText(categoria.replace("%30",
										"ñ"));

								c3.imagen.setImageResource(R.drawable.ok);
								c3.setBackgroundResource(R.drawable.fondoincverde);
								c3.seleccionaX.setTextColor(Color.DKGRAY);

								c4.imagen.setImageResource(R.drawable.no);
								c4.setBackgroundResource(R.drawable.grey_wash_wall_2);
								c4.resultadoX.setText("");
								c4.seleccionaX.setTextColor(Color.WHITE);

							}
						});
				builder.show();
			}
			if (pulsado == 4) {
				while (bloqueo == 0)
					;
				final resultado[] categorias = Herramientas
						.parsearSubCategoria(resultJson);
				final String[] nombreCategoria = new String[categorias.length];

				for (int i = 0; i < categorias.length; i++) {
					nombreCategoria[i] = new String(categorias[i].cadena);
				}

				AlertDialog.Builder builder = new AlertDialog.Builder(
						NuevoBuscador.this);
				builder.setTitle("Selecciona SubCategoría");
				builder.setItems(nombreCategoria,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								subcategoria = categorias[which].cadena;
								subcategoriaId = categorias[which].valor;

								c4.resultadoX.setText(subcategoria.replace(
										"%20", " "));
								c4.resultadoX.setText(subcategoria.replace(
										"%30", "ñ"));

								c4.imagen.setImageResource(R.drawable.ok);
								c4.setBackgroundResource(R.drawable.fondoincverde);
								c4.seleccionaX.setTextColor(Color.DKGRAY);

								Intent intent = new Intent(NuevoBuscador.this,
										ListadoProfesores.class);
								intent.putExtra("idMunicipio", municipioId);
								intent.putExtra("subcategoriaID",
										subcategoriaId);
								startActivity(intent);
							}
						});
				builder.show();

			}
		}

	}

}
