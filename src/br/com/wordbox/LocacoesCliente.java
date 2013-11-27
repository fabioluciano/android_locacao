package br.com.wordbox;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LocacoesCliente extends Activity {
	private long idClienteSelecionado;
	LocacaoDAO locacaoDAO;
	private ListView lv_locacoes;
	public List<Locacao> locacoes;
	AdapterListViewLocacoes dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locacoes_cliente);
		locacaoDAO = new LocacaoDAO(this);

		this.idClienteSelecionado = getIntent().getExtras()
				.getLong("idCliente");
		TextView nomeCliente = (TextView) findViewById(R.id.str_nome);
		TextView telefoneCliente = (TextView) findViewById(R.id.str_telefone);
		lv_locacoes = (ListView) findViewById(R.id.locacoes_cliente);
		Cliente cliente = locacaoDAO.getClienteById(idClienteSelecionado);
		this.locacoes = locacaoDAO.getLocacoesByCliente(cliente);

		nomeCliente.setText(cliente.getNome());
		telefoneCliente.setText(cliente.getTelefone());

		if (locacoes.size() > 0) {
			this.dataAdapter = new AdapterListViewLocacoes(
					this, this.locacoes);
			this.lv_locacoes.setAdapter(this.dataAdapter);
		} else {
			Toast.makeText(this, "Nenhuma locação encontrada",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void onResume() {
		super.onResume();
		locacaoDAO = new LocacaoDAO(this);
		Cliente cliente = locacaoDAO.getClienteById(idClienteSelecionado);
		this.locacoes = locacaoDAO.getLocacoesByCliente(cliente);
		this.dataAdapter = new AdapterListViewLocacoes(
				this, this.locacoes);
		this.lv_locacoes.setAdapter(this.dataAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_efetuar_locacao, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.ab_adicionar_locacao:
			Intent intent = new Intent(this, AdicionarLocacao.class);
			intent.putExtra("idCliente", this.idClienteSelecionado);
			startActivity(intent);
			return true;
		case R.id.ab_remover_cliente:
			locacaoDAO = new LocacaoDAO(this);
			Cliente cliente = locacaoDAO
					.getClienteById(this.idClienteSelecionado);
			locacaoDAO.removeCliente(cliente);
			Toast.makeText(getApplicationContext(),
					"Registro removido com sucesso", Toast.LENGTH_SHORT).show();
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
