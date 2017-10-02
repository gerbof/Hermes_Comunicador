package hermes.laboratorio.hermesbofsivori;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MainActivity extends Activity {

    List<Alumno> alumnosList=new ArrayList<Alumno>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarLista();
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) this.findViewById(R.id.list);
        TextView link = (TextView) this.findViewById(R.id.link);
        ListAdapter adapter = new ArrayAdapter<Alumno>(this, android.R.layout.simple_list_item_1, alumnosList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, EditableActivity.class);
                intent.putExtra("alumno", adapterView.getAdapter().getItem(position).toString());
                startActivity(intent);
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NuevoAlumnoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializarLista() {
        alumnosList.add(new Alumno("Matias", "Lopez"));
        alumnosList.add(new Alumno("Mariano", "Garatino"));
        alumnosList.add(new Alumno("Gertrudis", "Gimenez"));
        alumnosList.add(new Alumno("Matias", "Lopez"));
        alumnosList.add(new Alumno("Mariano", "Garatino"));
        alumnosList.add(new Alumno("Gertrudis", "Gimenez"));
        alumnosList.add(new Alumno("Matias", "Lopez"));
        alumnosList.add(new Alumno("Mariano", "Garatino"));
        alumnosList.add(new Alumno("Gertrudis", "Gimenez"));
    }

    private HashMap<String, Alumno> crearAlumno(String key, Alumno alumno) {
        HashMap<String, Alumno> alumnoMap= new HashMap<String, Alumno>();
        alumnoMap.put(key, alumno);
        return alumnoMap;
    }
}
