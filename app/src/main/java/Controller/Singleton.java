package Controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {

    private RequestQueue queue;
    private Context context;

    private static Singleton instancia;

    public Singleton(Context contexto){

        this.context = contexto;
        queue = getRequestQueue();

    }

    public RequestQueue getRequestQueue(){

        if (queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

    public static synchronized Singleton getInstance(Context context1){
        if (instancia == null){
            instancia = new Singleton(context1);
        }
        return instancia;
    }

    public <T> void addtoRequestQueue(Request request){
        queue.add(request);
    }

}
