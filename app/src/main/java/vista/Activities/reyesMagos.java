package vista.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.lostzone.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.Alumno;
import modelo.Rey;

public class reyesMagos extends AppCompatActivity {

    RecyclerView recycler;
    reyesAdapter adapter;
    List<Rey> reyes = new ArrayList<>();
    TextView reyesMagos;
    List<Alumno> alumnos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reyes_magos);
        cargar();
    }

    public void cargar(){

        recycler = findViewById(R.id.recicler_reyes);
        reyesMagos = findViewById(R.id.txt_titulo);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(getResources().openRawResource(R.raw.reyes), null);
            NodeList godos = doc.getElementsByTagName("godo");

            for (int i = 0; i < godos.getLength(); i++){

                Node itemNode = godos.item(i);

                if (itemNode.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element) itemNode;
                    Rey godo = new Rey();
                    godo.setGodo(elemento.getElementsByTagName("nombre").item(0).getTextContent());
                    godo.setPeriodo(elemento.getElementsByTagName("periodo").item(0).getTextContent());
                    reyes.add(godo);
                }

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new reyesAdapter(reyes);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

    }





}
