package ejemplo.codictados.mvp.iu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.ObjectGraph;
import ejemplo.codictados.mvp.R;
import ejemplo.codictados.mvp.contratos.Contrato;
import ejemplo.codictados.mvp.dagger.MainModule;

public class MainActivity extends AppCompatActivity implements Contrato.Vista {


    @Inject Contrato.Presentador presentador;

    private FloatingActionButton fab;
    private EditText etOperando1;
    private EditText etOperando2;
    private TextView tvOperacion;
    private Button btnSumar;
    private Button btnRestar;
    private Button btnMultiplicar;
    private Button btnDividir;
    private TextView tvResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity_contenedor);

        // Inyecta las clases con Dagger. Esto solo lo tenemos aquí por simplicidad.
        ObjectGraph objectGraph = ObjectGraph.create(new MainModule());
        objectGraph.inject(this);

        // Le dice al presenter cuál es su vista
        presentador.setVista(this);

        loadView();
    }


    public void loadView(){

        fab = (FloatingActionButton) findViewById(R.id.realizar_operacion);
        etOperando1 = (EditText) findViewById(R.id.et_operando1);
        etOperando2 = (EditText) findViewById(R.id.et_operando2);
        tvOperacion = (TextView) findViewById(R.id.tv_operacion);
        btnSumar = (Button) findViewById(R.id.btn_sumar);
        btnRestar = (Button) findViewById(R.id.btn_restar);
        btnMultiplicar = (Button) findViewById(R.id.btn_multiplicar);
        btnDividir = (Button) findViewById(R.id.btn_dividir);
        tvResultado = (TextView) findViewById(R.id.tv_resultado);


        // Ante un evento se llama al presenter para solicitar que se realice la operacion.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentador.calcularResultado();
            }
        });


        // Al pulsar cualquier botón de operación, el presenter almacenará el tipo de operación que
        // se realizará.
        btnSumar.setOnClickListener(listenerSeleccionarOperacion);
        btnRestar.setOnClickListener(listenerSeleccionarOperacion);
        btnMultiplicar.setOnClickListener(listenerSeleccionarOperacion);
        btnDividir.setOnClickListener(listenerSeleccionarOperacion);
    }

    private View.OnClickListener listenerSeleccionarOperacion = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            presentador.setTipoOperacion(view);
        }
    };


    @Override
    public void getOperandos(String[] operandos) {
        operandos[0] = etOperando1.getText().toString();
        operandos[1] = etOperando2.getText().toString();
    }

    // El presentador llamará a este método para devolver el resultado de la operación solicitada.
    @Override
    public void mostrarResultado(int resultado){
        tvResultado.setText(String.valueOf(resultado));
    }

    // El presentador llamará a este método para devolver el carácter de la operación marcada.
    @Override
    public void mostrarTipoOperacion(char caracter){
        tvOperacion.setText(String.valueOf(caracter));
    }

    @Override
    public void mostrarError(String error) {
        Snackbar.make(fab, error, Snackbar.LENGTH_SHORT).show();
    }
}
