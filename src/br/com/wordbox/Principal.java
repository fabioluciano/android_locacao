package br.com.wordbox;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Principal extends Activity {
	ListView lv_clientes;
	LocacaoDAO locacaoDAO;
	AdapterListViewClientes dataAdapter;
	private static List<Cliente> clientes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		this.lv_clientes = (ListView) findViewById(R.id.lv_clientes);
		locacaoDAO = new LocacaoDAO(this);
		Principal.clientes = locacaoDAO.getAllClientes();

		if (Principal.clientes.size() > 0) {
			this.dataAdapter = new AdapterListViewClientes(this, this.clientes);
			this.lv_clientes.setAdapter(this.dataAdapter);

			this.lv_clientes.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(getApplicationContext(),
							LocacoesCliente.class);
					intent.putExtra("idCliente",
							Principal.clientes.get(position).getId());
					startActivity(intent);
				}
			});

		} else {
			Toast.makeText(this, "Nenhum cliente encontrado",
					Toast.LENGTH_SHORT).show();
		}

	}

	public void onResume() {
		super.onResume();
		locacaoDAO = new LocacaoDAO(this);
		Principal.clientes = locacaoDAO.getAllClientes();
		this.dataAdapter = new AdapterListViewClientes(this, this.clientes);
		this.lv_clientes.setAdapter(this.dataAdapter);
		this.lv_clientes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getApplicationContext(),
						LocacoesCliente.class);
				intent.putExtra("idCliente", Principal.clientes.get(position)
						.getId());
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.ab_adicionar_cliente:
			Intent intent = new Intent(getApplicationContext(),
					AdicionarCliente.class);
			startActivity(intent);
			return true;
		case R.id.ab_adicionar_automovel:
			Intent intenta = new Intent(getApplicationContext(),
					AdicionarAutomovel.class);
			startActivity(intenta);
			return true;
		case R.id.ab_adicionar_locacao:
			Intent intents = new Intent(getApplicationContext(),
					AdicionarLocacao.class);
			startActivity(intents);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
