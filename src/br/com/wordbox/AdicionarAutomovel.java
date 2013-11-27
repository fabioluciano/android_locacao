package br.com.wordbox;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarAutomovel extends Activity {
	
	LocacaoDAO locacaoDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_automovel);
		locacaoDAO = new LocacaoDAO(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_salvar_cancelar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.ab_salvar:
				EditText cmp_modelo = (EditText) findViewById(R.id.cmp_modelo);
				EditText cmp_ano = (EditText) findViewById(R.id.cmp_ano);
				EditText cmp_placa = (EditText) findViewById(R.id.cmp_placa);
				EditText cmp_preco = (EditText) findViewById(R.id.cmp_preco);
				
				short ano = Short.valueOf(cmp_ano.getText().toString());
				float preco = Float.valueOf(cmp_preco.getText().toString()); 
				
				Automovel automovel = new Automovel(
						cmp_placa.getText().toString(),
						cmp_modelo.getText().toString(),
						ano,
						preco);
				locacaoDAO.insertAutomovel(automovel);
				
				Toast.makeText(getApplicationContext(), "Registro salvo com sucesso",
						Toast.LENGTH_SHORT).show();
				
				finish();
	            return true;
	        case R.id.ab_cancelar:
				finish();
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
