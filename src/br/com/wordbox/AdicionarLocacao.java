package br.com.wordbox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdicionarLocacao extends Activity implements OnItemSelectedListener {
	private LocacaoDAO locacaoDAO;
	private long idClienteSelecionado;
	private long idAutomovelSelecionado;
	private List<Automovel> automoveis;
	
	Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_locacao);
		this.idClienteSelecionado = getIntent().getExtras().getLong("idCliente");
		spinner = (Spinner) findViewById(R.id.sp_automoveis);
		spinner.setOnItemSelectedListener(this);

		carregarSpinner();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_salvar_cancelar, menu);
		return true;
	}

	public void carregarSpinner() {
		this.locacaoDAO = new LocacaoDAO(this);
		this.automoveis = locacaoDAO.getAllAutomoveis();

		ArrayAdapter<Automovel> dataAdapter = new ArrayAdapter<Automovel>(this,
				android.R.layout.simple_spinner_item, this.automoveis);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		this.idAutomovelSelecionado = this.automoveis.get(pos).getId();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.ab_salvar:
	        	this.insertLocacao();
				finish();
	            return true;
	        case R.id.ab_cancelar:
				finish();
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void insertLocacao() {
		this.locacaoDAO = new LocacaoDAO(this);
    	Cliente cliente = this.locacaoDAO.getClienteById(this.idClienteSelecionado);
    	Automovel automovel = this.locacaoDAO.getAutomovelById(this.idAutomovelSelecionado);
 
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	String currentDateandTime = sdf.format(new Date());
    	
    	Locacao locacao = new Locacao(cliente, automovel, currentDateandTime);
    	
		this.locacaoDAO.insertLocacao(locacao);
	}

}
