package br.com.wordbox;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListViewClientes extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Cliente> itens;

	public AdapterListViewClientes(Context context, List<Cliente> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itens.size();
	}

	public Cliente getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		Cliente item = itens.get(position);
		view = mInflater.inflate(R.layout.lvl_clientes, null);
		((TextView) view.findViewById(R.id.cmp_nome)).setText(item.getNome());
		((TextView) view.findViewById(R.id.cmp_telefone)).setText(item.getTelefone());
		((TextView) view.findViewById(R.id.cmp_endereco)).setText(item.getEndereco());
		
		return view;
	}
}