package dotidapp.ineedclass;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        final ImageButton buttonBusqueda = (ImageButton) findViewById(R.id.btnBusqueda);
        buttonBusqueda.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
            	//Intent intent = new Intent(MainActivity.this, BusquedaProvincia.class);
        		Intent intent = new Intent(MainActivity.this, NuevoBuscador.class);
                startActivity(intent);
            }
        });
           
        
        final ImageButton buttonRegistro = (ImageButton) findViewById(R.id.btnRegistro);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });
        
        final ImageButton buttonCompartir = (ImageButton) findViewById(R.id.btnCompartir);
        buttonCompartir.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
        		try {
					 Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND); 
					 shareIntent.setType("text/plain"); 
					 shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.textoCompartir)); 
					 v.getContext().startActivity(Intent.createChooser(shareIntent, "Compartir via...")); 
				}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
        });
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
