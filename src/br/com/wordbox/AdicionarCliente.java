package br.com.wordbox;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarCliente extends Activity {
	private LocacaoDAO locacaoDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_cliente);
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
			EditText cmp_nome = (EditText) findViewById(R.id.cmp_nome);
			EditText cmp_telefone = (EditText) findViewById(R.id.cmp_telefone);
			EditText cmp_endereco = (EditText) findViewById(R.id.cmp_endereco);

			Cliente cliente = new Cliente(
					cmp_nome.getText().toString(),
					cmp_telefone.getText().toString(),
					cmp_endereco.getText().toString()
					);
			locacaoDAO.insertCliente(cliente);

			Toast.makeText(getApplicationContext(),
					"Registro salvo com sucesso", Toast.LENGTH_SHORT).show();

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
