package hermes.laboratorio.hermesbofsivori;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class NuevoAlumnoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumnoactivity);

        //RadioGroup sexo = (RadioGroup) this.findViewById(R.id.radiogroup);

        Button aceptar = (Button) this.findViewById(R.id.button);
        Button cancelar = (Button) this.findViewById(R.id.button2);

        aceptar.setOnClickListener(new evento());
        cancelar.setOnClickListener(new evento());


    }

    private class evento implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String option = ((Button) v).getText().toString();
            if(option.equals("Guardar")){
                System.out.println("ACA TE GUARDO");
            }
            onBackPressed();
        }
    }
}
