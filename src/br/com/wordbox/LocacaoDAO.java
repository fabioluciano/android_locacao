package br.com.wordbox;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocacaoDAO extends DatabaseHandler {

	private SQLiteDatabase databaseRO;
	private SQLiteDatabase databaseRW;

	public LocacaoDAO(Context context) {
		super(context);
		this.databaseRO = this.getReadableDatabase();
		this.databaseRW = this.getWritableDatabase();
	}

	public void insertAutomovel(Automovel automovel) {
		try {
			databaseRW.execSQL("INSERT INTO " + TBL_AUTOMOVEIS
					+ " (modelo, ano, preco, placa) VALUES " + "('"
					+ automovel.getModelo() + "', '" + automovel.getAno()
					+ "', '" + automovel.getPreco() + "', '"
					+ automovel.getPlaca() + "')");
		} catch (Exception erro) {

		}
	}

	public void insertCliente(Cliente cliente) {
		try {
			databaseRW.execSQL("INSERT INTO " + TBL_CLIENTES
					+ " (nome, telefone, endereco) VALUES " + "('"
					+ cliente.getNome() + "', '" + cliente.getTelefone()
					+ "', '" + cliente.getEndereco() + "')");
		} catch (Exception erro) {

		}
	}

	public void insertLocacao(Locacao locacao) {
		try {
			databaseRW.execSQL("INSERT INTO " + TBL_LOCACOES
					+ " (id_cliente, id_automovel, data_locacao) VALUES "
					+ "('" + locacao.getCliente().getId() + "', '"
					+ locacao.getAutomovel().getId() + "', '"
					+ locacao.getDataLocacao() + "')");
		} catch (Exception erro) {

		}
	}

	public void removeCliente(Cliente cliente) {
		try {
			databaseRW.execSQL("DELETE FROM " + TBL_CLIENTES + " WHERE _id = "
					+ cliente.getId());
		} catch (Exception erro) {

		}
	}

	public void removeAutomovel(Automovel automovel) {
		try {
			databaseRW.execSQL("DELETE FROM " + TBL_CLIENTES + " WHERE _id = "
					+ automovel.getId());
		} catch (Exception erro) {

		}
	}

	public void removeLocacao(Locacao locacao) {
		try {
			databaseRW.execSQL("DELETE FROM " + TBL_CLIENTES + " WHERE _id = "
					+ locacao.getId());
		} catch (Exception erro) {

		}
	}

	public Cliente getClienteById(long id) {
		String SQL = "SELECT * FROM " + TBL_CLIENTES + " WHERE _id = " + id;
		Cursor cursor = databaseRO.rawQuery(SQL, null);

		cursor.moveToFirst();

		return new Cliente(cursor.getInt(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3));
	}

	public Automovel getAutomovelById(long id) {
		String SQL = "SELECT * FROM " + TBL_AUTOMOVEIS + " WHERE _id = " + id;
		Cursor cursor = databaseRO.rawQuery(SQL, null);

		cursor.moveToFirst();

		return new Automovel(cursor.getInt(0), cursor.getString(4),
				cursor.getString(1), cursor.getShort(2), cursor.getShort(2));
	}

	public Locacao getLocacaoById(long id) {
		String SQL = "SELECT * FROM " + TBL_LOCACOES + " WHERE _id = " + id;
		Cursor cursor = databaseRO.rawQuery(SQL, null);

		cursor.moveToFirst();
		Cliente cliente = this.getClienteById(cursor.getInt(1));
		Automovel automovel = this.getAutomovelById(cursor.getInt(2));

		return new Locacao(cursor.getInt(0), cliente, automovel,
				cursor.getString(3));
	}

	public List<Locacao> getLocacoesByCliente(Cliente cliente) {
		List<Locacao> clientes = new ArrayList<Locacao>();
		String query = "SELECT * FROM " + TBL_LOCACOES + " WHERE id_cliente = "
				+ cliente.getId();
		Cursor cursor = databaseRO.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				Automovel automovel = this.getAutomovelById(cursor.getInt(2));

				Locacao locacao = new Locacao(id, cliente, automovel,
						cursor.getString(3));

				clientes.add(locacao);
			} while (cursor.moveToNext());
		}
		cursor.close();
		databaseRO.close();

		return clientes;
	}

	public List<Cliente> getAllClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		String query = "SELECT * FROM " + TBL_CLIENTES;
		Cursor cursor = databaseRO.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String nome = cursor.getString(1);
				String telefone = cursor.getString(2);
				String endereco = cursor.getString(3);

				Cliente cliente = new Cliente(id, nome, telefone, endereco);

				clientes.add(cliente);
			} while (cursor.moveToNext());
		}
		cursor.close();
		databaseRO.close();

		return clientes;
	}

	public List<Automovel> getAllAutomoveis() {
		List<Automovel> automoveis = new ArrayList<Automovel>();
		String query = "SELECT * FROM " + TBL_AUTOMOVEIS;
		;
		Cursor cursor = databaseRO.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				String modelo = cursor.getString(1);
				short ano = cursor.getShort(2);
				float preco = cursor.getFloat(3);
				String placa = cursor.getString(4);

				Automovel automovel = new Automovel(cursor.getInt(0), placa,
						modelo, ano, preco);

				automoveis.add(automovel);
			} while (cursor.moveToNext());
		}
		cursor.close();
		databaseRO.close();

		return automoveis;
	}

	public List<Locacao> getAllLocacoes() {
		List<Locacao> locacoes = new ArrayList<Locacao>();
		String SQL = "SELECT * FROM locacoes";
		Cursor cursor = databaseRO.rawQuery(SQL, null);

		if (cursor.moveToFirst()) {
			do {

				Cliente cliente = this.getClienteById(cursor.getInt(1));
				Automovel automovel = this.getAutomovelById(cursor.getInt(2));

				Locacao locacao = new Locacao(cursor.getInt(0), cliente,
						automovel, cursor.getString(3));

				locacoes.add(locacao);
			} while (cursor.moveToNext());
		}
		cursor.close();
		databaseRO.close();

		return locacoes;
	}
}
