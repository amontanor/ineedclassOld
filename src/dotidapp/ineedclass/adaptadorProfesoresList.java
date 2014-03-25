package dotidapp.ineedclass;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adaptadorProfesoresList extends BaseAdapter {

	public adaptadorProfesoresList(Activity activity,
			ArrayList<filaProfesor> items) {
		super();
		this.activity = activity;
		this.items = items;
	}

	protected Activity activity;
	protected ArrayList<filaProfesor> items;

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Generamos una convertView por motivos de eficiencia
		View v = convertView;

		// Asociamos el layout de la lista que hemos creado
		if (convertView == null) {
			LayoutInflater inf = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.filaprofesor, null);
		}

		filaProfesor dir = items.get(position);
		// Rellenamos la fotografía
		ImageView foto = (ImageView) v.findViewById(R.id.imageTipo);
		foto.setImageDrawable(dir.getImagen());
		// Rellenamos el nombre
		TextView nombre = (TextView) v.findViewById(R.id.nombreProfe);
		nombre.setText(dir.getNombre());
		// Rellenamos el cargo
		TextView cargo = (TextView) v.findViewById(R.id.precio);
		cargo.setText(dir.getPrecio());

		// Retornamos la vista
		return v;
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}
}
