package dotidapp.ineedclass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.StrictMode;
import android.util.Log;

public class Herramientas {

	static InputStream is = null;
	static String json = "";
	private static JSONArray jArray;

	public static String jsonLoad(String url) {

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		try {
			// defaultHttpClient

			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		return json;
	}

	public static resultado[] parsear(String cadena) {
		resultado resultados[] = null;
		try {
			jArray = new JSONArray(cadena);
			resultados = new resultado[jArray.length()];

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				aux.cadena = jArray.getJSONObject(i).getString("provincia")
						.replace("%20", " ");
				aux.cadena = aux.cadena.replace("%30", "ñ");
				aux.valor = Integer.parseInt(jArray.getJSONObject(i).getString(
						"idprovincia"));
				resultados[i] = aux;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultados;

	}

	public static resultado[] parsearCategoria(String cadena) {
		resultado resultados[] = null;
		try {
			jArray = new JSONArray(cadena);
			resultados = new resultado[jArray.length()];

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				aux.cadena = jArray.getJSONObject(i).getString("nombre")
						.replace("%20", " ");
				aux.cadena = aux.cadena.replace("%30", "ñ");
				aux.valor = Integer.parseInt(jArray.getJSONObject(i).getString(
						"id"));
				resultados[i] = aux;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultados;

	}

	public static resultado[] parsearListadoProfesores(String cadena) {
		resultado resultados[] = null;
		try {
			jArray = new JSONArray(cadena);
			resultados = new resultado[jArray.length()];

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				aux.cadena = jArray.getJSONObject(i).getString("nombre")
						.replace("%20", " ");
				aux.cadena = aux.cadena.replace("%30", "ñ");
				aux.cadena2 = jArray.getJSONObject(i).getString("apellido1")
						.replace("%20", " ");
				aux.cadena2 = aux.cadena2.replace("%30", "ñ");
				aux.valor = Integer.parseInt(jArray.getJSONObject(i).getString(
						"idusuario"));
				aux.precio = jArray.getJSONObject(i).getString("precio");
				aux.tipo = jArray.getJSONObject(i).getInt("idtipo");
				aux.id = jArray.getJSONObject(i).getString("id");
				resultados[i] = aux;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return resultados;

	}

	public static resultado[] parsearProvincia(String cadena) {
		resultado resultados[] = null;
		try {
			jArray = new JSONArray(cadena);
			resultados = new resultado[jArray.length()];

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				aux.cadena = jArray.getJSONObject(i).getString("poblacion")
						.replace("%20", " ");
				aux.cadena = aux.cadena.replace("%30", "ñ");
				aux.valor = Integer.parseInt(jArray.getJSONObject(i).getString(
						"idpoblacion"));
				resultados[i] = aux;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultados;

	}
	
	public static Profesor parsearProfesorIntercambio(String cadena) {
		Profesor profe = new Profesor();
		try {
			jArray = new JSONArray(cadena);

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				profe.mail = jArray.getJSONObject(i).getString("mail")
						.replace("%20", " ").replace("%30", "ñ");
				profe.telefono = jArray.getJSONObject(i).getString("telefono")
						.replace("%20", " ").replace("%30", "ñ");
				profe.busco = jArray.getJSONObject(i).getString("idmateriaintercambio1")
						.replace("%20", " ").replace("%30", "ñ");
				profe.ofrezco = jArray.getJSONObject(i).getString("idmateriaintercambio2")
						.replace("%20", " ").replace("%30", "ñ");
				profe.comentarios = jArray.getJSONObject(i).getString("comentario")
						.replace("%20", " ").replace("%30", "ñ");
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profe;
	}
	public static Profesor parsearProfesor(String cadena) {
		Profesor profe = new Profesor();
		try {
			jArray = new JSONArray(cadena);

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				profe.nombre = jArray.getJSONObject(i).getString("nombre")
						.replace("%20", " ").replace("%30", "ñ");
				profe.comentarios = jArray.getJSONObject(i)
						.getString("comentario").replace("%20", " ")
						.replace("%30", "ñ");
				profe.nivel = jArray.getJSONObject(i).getString("nivel");
				profe.mail = jArray.getJSONObject(i).getString("mail")
						.replace("%20", " ").replace("%30", "ñ");
				profe.precio = jArray.getJSONObject(i).getString("precio");
				profe.titulacion = jArray.getJSONObject(i)
						.getString("cursando").replace("%20", " ")
						.replace("%30", "ñ");
				profe.telefono = jArray.getJSONObject(i).getString("telefono");
				profe.apellido = jArray.getJSONObject(i).getString("apellido1")
						.replace("%20", " ").replace("%30", "ñ");

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profe;
	}

	public static resultado[] parsearSubCategoria(String cadena) {
		resultado resultados[] = null;
		try {
			jArray = new JSONArray(cadena);
			resultados = new resultado[jArray.length()];

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				aux.cadena = jArray.getJSONObject(i).getString("nombre")
						.replace("%20", " ");
				aux.cadena = aux.cadena.replace("%30", "ñ");
				aux.valor = Integer.parseInt(jArray.getJSONObject(i).getString(
						"id"));
				resultados[i] = aux;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultados;
	}

	public static resultado[] parsearListadoProfesoresIntercambio(String cadena) {
		resultado resultados[] = null;
		try {
			jArray = new JSONArray(cadena);
			resultados = new resultado[jArray.length()];

			for (int i = 0; i < jArray.length(); i++) {
				resultado aux = new resultado();
				aux.cadena = jArray.getJSONObject(i).getString("nombre")
						.replace("%20", " ");
				aux.cadena = aux.cadena.replace("%30", "ñ");
				aux.cadena2 = jArray.getJSONObject(i).getString("apellido1")
						.replace("%20", " ");
				aux.cadena2 = aux.cadena2.replace("%30", "ñ");
				aux.valor = Integer.parseInt(jArray.getJSONObject(i).getString(
						"idusuario"));
				aux.precio = jArray.getJSONObject(i).getString("precio");
				resultados[i] = aux;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultados;
	}

}
