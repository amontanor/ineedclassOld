package dotidapp.ineedclass;

import org.w3c.dom.Text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class componenteSeleccion extends LinearLayout {

	TextView seleccionaX;
	TextView vacio;
	TextView resultadoX;
	ImageView imagen;
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public componenteSeleccion(Context context, AttributeSet attrs) {
		super(context, attrs);
		seleccionaX = new TextView(this.getContext());
		resultadoX = new TextView(this.getContext());
		imagen = new ImageView(this.getContext());
		vacio = new TextView(this.getContext());
		// this.initUI(attrs);
	}

	public void initUI(String seleccionaX, String resultado, int imagen,int tamanoImagen,int id) {
		
		this.id=id;
		
		this.seleccionaX.setText(seleccionaX);
		this.seleccionaX.setTextColor(Color.WHITE);
		this.seleccionaX.setTextSize(20);

		this.addView(this.seleccionaX);

		this.resultadoX.setText(resultado);
		this.addView(this.resultadoX);

		if (imagen == 1){
			this.imagen.setImageResource(R.drawable.no);
			this.setBackgroundDrawable(getResources().getDrawable(R.drawable.grey_wash_wall_2));
		}
		else{
			this.imagen.setImageResource(R.drawable.ok);
			this.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoincverde));
		}
		
		//Vacio
		this.vacio.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, 
                        LinearLayout.LayoutParams.WRAP_CONTENT)); 
		this.vacio.getLayoutParams().height = tamanoImagen;
		this.vacio.getLayoutParams().width = tamanoImagen;
		
		this.addView(this.vacio);
		//Fin Vacio

		this.imagen.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, 
                        LinearLayout.LayoutParams.WRAP_CONTENT)); 
		this.imagen.getLayoutParams().height = tamanoImagen;
		this.imagen.getLayoutParams().width = tamanoImagen;
		
		this.addView(this.imagen);

		this.setOrientation(LinearLayout.VERTICAL);
		this.setGravity(Gravity.CENTER_HORIZONTAL);
	}

}
