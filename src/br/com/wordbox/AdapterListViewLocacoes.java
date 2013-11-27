package br.com.wordbox;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListViewLocacoes extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Locacao> itens;

	public AdapterListViewLocacoes(Context context, List<Locacao> itens) {
		this.itens = itens;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itens.size();
	}

	public Locacao getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		Locacao item = itens.get(position);
		view = mInflater.inflate(R.layout.lvl_locacoes, null);
		((TextView) view.findViewById(R.id.cmp_modelo)).setText(item.getAutomovel().getModelo());
		((TextView) view.findViewById(R.id.cmp_placa)).setText(item.getAutomovel().getPlaca());
		((TextView) view.findViewById(R.id.cmp_data_locacao)).setText(item.getDataLocacao());
		
		return view;
	}
}