package br.com.wordbox;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DatabaseHandler extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "locacao.db";
	private static final int DATABASE_VERSION = 10;
	private static final String CREATE_CLIENTE = "CREATE TABLE IF NOT EXISTS clientes ("
			+ "_id INTEGER PRIMARY KEY,"
			+ "nome VARCHAR(100) NOT NULL,"
			+ "telefone VARCHAR(50) NOT NULL,"
			+ "endereco TEXT NOT NULL"
			+ ")";

	private static final String CREATE_AUTOMOVEL = "CREATE TABLE IF NOT EXISTS automoveis ("
			+ "_id INTEGER PRIMARY KEY,"
			+ "modelo VARCHAR(100) NOT NULL,"
			+ "ano INTEGER NOT NULL,"
			+ "preco FLOAT NOT NULL, "
			+ "placa VARCHAR(10) NOT NULL)";

	private static final String CREATE_LOCACAO = "CREATE TABLE IF NOT EXISTS locacoes ("
			+ "_id INTEGER PRIMARY KEY,"
			+ "id_cliente INTEGER NOT NULL,"
			+ "id_automovel INTEGER NOT NULL,"
			+ "data_locacao VARCHAR(100) NOT NULL)";
	protected static final String TBL_CLIENTES = "clientes";
	protected static final String TBL_AUTOMOVEIS = "automoveis";
	protected static final String TBL_LOCACOES = "locacoes";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_CLIENTE);
		database.execSQL(CREATE_AUTOMOVEL);
		database.execSQL(CREATE_LOCACAO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS clientes");
		db.execSQL("DROP TABLE IF EXISTS automoveis");
		db.execSQL("DROP TABLE IF EXISTS locacoes");
		onCreate(db);
	}
}
