package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import modelo.Producto;

public class HelperProducto extends SQLiteOpenHelper {


    public HelperProducto(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table producto(" +
                "id INTEGER Primary Key AUTOINCREMENT," +
                "nombre varchar(40)," +
                "codigo int(5)," +
                "descripcion longtext," +
                "precio decimal(4, 2)," +
                "cantidad int(5))");

    }

    public void insertar(Producto p){
        ContentValues values = new ContentValues();
        values.put("codigo", p.getCodigo());
        values.put("nombre", p.getNombre());
        values.put("descripcion", p.getDespcripcion());
        values.put("precio", p.getPrecio());
        values.put("cantidad", p.getCantidad());

        this.getWritableDatabase().insert("producto", null, values);

    }

    public List<Producto> getAll(){

        List<Producto> lista = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery("select * from producto", null);

        if (cursor.moveToFirst()){
            do{
                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setDespcripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                p.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                lista.add(p);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public void modificar(Producto producto){

        ContentValues valores = new ContentValues();

        valores.put("codigo", producto.getCodigo());
        valores.put("descripcion", producto.getDespcripcion());
        valores.put("precio", producto.getPrecio());
        valores.put("cantidad", producto.getCantidad());
        valores.put("nombre", producto.getNombre());

        this.getWritableDatabase().update("producto", valores, "codigo = " + "'" +producto.getCodigo() + "'", null);

    }

    public void eliminarProductobycode(String code){

        this.getWritableDatabase().delete("producto", "codigo = "+ "'" +code+ "'", null);

    }

    public void eliminarTodo(){
        this.getWritableDatabase().delete("producto", null, null);
    }

    public List<Producto> getProductbyID(String code){

        List<Producto> lista = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery("select * from producto where codigo ="  + code , null);

        if (cursor.moveToFirst()){

            do {

                Producto p = new Producto();
                p.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                p.setPrecio(cursor.getDouble(cursor.getColumnIndex("precio")));
                p.setDespcripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                p.setCantidad(cursor.getInt(cursor.getColumnIndex("cantidad")));
                p.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                lista.add(p);

            } while (cursor.moveToNext());

        }

        cursor.close();

        return lista;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
