package dotidapp.ineedclass;

import dotidapp.ineedclass.Herramientas;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BusquedaProvincia extends Activity {
	
	final Context context = this;
	String ciudad, provincia,idioma,categoria,subcategoria;
	int ciudadId,provinciaId,idiomaID,categoriaID,subcategoriaID;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        

		setContentView(R.layout.activity_busqueda_provincia);	
		
		Button btnProvincia = (Button)findViewById(R.id.btnProvincia);
		Button btnCiudad = (Button)findViewById(R.id.btnCiudad);
		Button btnIdioma = (Button)findViewById(R.id.btnIdioma);
		Button btnSubCategoria = (Button)findViewById(R.id.btnSubCategoria);
		Button btnListarProfesores = (Button)findViewById(R.id.btnListarProfesores);

		
		
		///////BTN PROVINCIA
		

		btnProvincia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String resultJson= Herramientas.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=2&texto="+String.valueOf(ciudadId));
            	final resultado[] provincias = Herramientas.parsearProvincia(resultJson);
            	final String[] nombreProvincias= new String[provincias.length];
            	
            	for (int i =0;i<provincias.length;i++){
            		nombreProvincias[i]= new String(provincias[i].cadena);
            	}
            	
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(context);
            	builder.setTitle("Selecciona Provincia");
            	builder.setItems(nombreProvincias, new DialogInterface.OnClickListener() {
            	    @Override
            	    public void onClick(DialogInterface dialog, int which) {
            	    	provincia = provincias[which].cadena;
            	    	provinciaId = provincias[which].valor;
            	        TextView textoProvincia = (TextView)findViewById(R.id.txtProvincia);
            	        textoProvincia.setText(provincia.replace("%20", " "));
            	        textoProvincia.setText(provincia.replace("%30", "ñ"));
            	        }
            	});
            	builder.show(); 
				
			}
            			
        });
		
	///////FIN  BTN CIUDAD
		
	///////BTN Ciudad	
		
		btnCiudad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String resultJson= Herramientas.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=1");
            	final resultado[] ciudades = Herramientas.parsear(resultJson);
            	final String[] nombreCiudades= new String[ciudades.length];
            	
            	for (int i =0;i<ciudades.length;i++){
            		nombreCiudades[i]= new String(ciudades[i].cadena);
            	}
            	
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(context);
            	builder.setTitle("Selecciona Ciudad");
            	builder.setItems(nombreCiudades, new DialogInterface.OnClickListener() {
            	    @Override
            	    public void onClick(DialogInterface dialog, int which) {
            	        ciudad = ciudades[which].cadena;
            	        ciudadId=ciudades[which].valor;
            	        TextView textoCiudad = (TextView)findViewById(R.id.txtCiudad);
            	        textoCiudad.setText(ciudad.replace("%20", " "));
            	        textoCiudad.setText(ciudad.replace("%30", "ñ"));
            	        }
            	});
            	builder.show(); 
				
			}
            			
        });
		
		
	///////FIN BTN PROVINCIA
		
		
	///////BTN IDIOMA	
		
		btnIdioma.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String resultJson= Herramientas.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=6&texto="+String.valueOf(provinciaId));
            	final resultado[] categorias = Herramientas.parsearCategoria(resultJson);
            	final String[] nombreCategoria= new String[categorias.length];
            	
            	for (int i =0;i<categorias.length;i++){
            		nombreCategoria[i]= new String(categorias[i].cadena);
            	}
            	
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(context);
            	builder.setTitle("Selecciona Categoría");
            	builder.setItems(nombreCategoria, new DialogInterface.OnClickListener() {
            	    @Override
            	    public void onClick(DialogInterface dialog, int which) {
            	    	idioma = categorias[which].cadena;
            	    	idiomaID = categorias[which].valor;
            	        TextView textoProvincia = (TextView)findViewById(R.id.txtCategoria);
            	        textoProvincia.setText(idioma);
            	        }
            	});
            	builder.show(); 
				
			}
            			
        });
		
		
	///////FIN BTN IDIOMA
		
		
	///////BTN SUBCATEGORIA
		
			btnSubCategoria.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String resultJson= Herramientas.jsonLoad("http://s425938729.mialojamiento.es/webs/inc/consultaAndroid.php?opcion=3&texto1="+String.valueOf(provinciaId)+"&texto2="+String.valueOf(idiomaID));
	            	final resultado[] categorias = Herramientas.parsearSubCategoria(resultJson);
	            	final String[] nombreCategoria= new String[categorias.length];
	            	
	            	for (int i =0;i<categorias.length;i++){
	            		nombreCategoria[i]= new String(categorias[i].cadena);
	            	}
	            	
	            	
	            	AlertDialog.Builder builder = new AlertDialog.Builder(context);
	            	builder.setTitle("Selecciona SubCategoría");
	            	builder.setItems(nombreCategoria, new DialogInterface.OnClickListener() {
	            	    @Override
	            	    public void onClick(DialogInterface dialog, int which) {
	            	    	subcategoria = categorias[which].cadena;
	            	    	subcategoriaID = categorias[which].valor;
	            	        TextView textoProvincia = (TextView)findViewById(R.id.txtSubCategoria);
	            	        textoProvincia.setText(subcategoria);
	            	        }
	            	});
	            	builder.show(); 
					
				}
	            			
	        });
			
			
		///////FIN BTN SUBCATEGORIA
		
		btnListarProfesores.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BusquedaProvincia.this, ListadoProfesores.class);
				intent.putExtra("idprovincia", provinciaId);
				intent.putExtra("subcategoriaID", subcategoriaID);
                startActivity(intent);				
			}
            			
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.busqueda_provincia, menu);
		return true;
	}

}
