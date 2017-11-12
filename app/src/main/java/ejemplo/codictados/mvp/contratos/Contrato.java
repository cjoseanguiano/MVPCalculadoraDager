package ejemplo.codictados.mvp.contratos;

import android.view.View;

public interface Contrato {

    interface Vista {

        void getOperandos(String[] operandos);
        void mostrarError(String error);
        void mostrarResultado(int text);
        void mostrarTipoOperacion(char caracter);
    }

    interface Presentador {

        void setVista(Vista vista);
        void setTipoOperacion(View view);
        void calcularResultado();
    }
}
