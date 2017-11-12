package ejemplo.codictados.mvp.presentadores;

import android.view.View;

import ejemplo.codictados.mvp.R;
import ejemplo.codictados.mvp.casos_uso.CasoUsoDivision;
import ejemplo.codictados.mvp.casos_uso.CasoUsoMultiplicacion;
import ejemplo.codictados.mvp.casos_uso.CasoUsoResta;
import ejemplo.codictados.mvp.casos_uso.CasoUsoSuma;
import ejemplo.codictados.mvp.casos_uso.Operacion;
import ejemplo.codictados.mvp.contratos.Contrato;
import ejemplo.codictados.mvp.modelos.Operando;
import ejemplo.codictados.mvp.modelos.Resultado;

public class Presentador implements Contrato.Presentador {

    public static final int SUMA = 0;
    public static final int RESTA = 1;
    public static final int MULTIPLICACION = 2;
    public static final int DIVISION = 3;
    public static final int NINGUNA = 4;

    Contrato.Vista vista;
    private int tipoOperacion = NINGUNA;

    // El presentador recibe su vista para devolverle cosas.
    @Override
    public void setVista(Contrato.Vista vista) {
        this.vista = vista;
    }


    // Cuando en la vista se hace clic en un botón de operación, el presenter se encarga de decidir
    // cuál es el tipo de operación que se va a realizar y qué caracter de operación debe mostrar.
    @Override
    public void setTipoOperacion(View view) {
        switch (view.getId()){
            case R.id.btn_sumar: tipoOperacion = SUMA; break;
            case R.id.btn_restar: tipoOperacion = RESTA; break;
            case R.id.btn_multiplicar: tipoOperacion = MULTIPLICACION; break;
            case R.id.btn_dividir: tipoOperacion = DIVISION; break;
            default: tipoOperacion = NINGUNA;
        }
        vista.mostrarTipoOperacion(obtenerCaracterOperacion());
    }

    public char obtenerCaracterOperacion(){
        char caracterOperacion;
        switch(tipoOperacion){
            case SUMA: caracterOperacion = '+'; break;
            case RESTA: caracterOperacion = '-'; break;
            case MULTIPLICACION: caracterOperacion = 'x'; break;
            case DIVISION: caracterOperacion = '/'; break;
            default: caracterOperacion = ' ';
        }
        return caracterOperacion;
    }


    // Cuando la vista pide al presentador el resultado de la operación, el presentador ejecutará
    // el caso de uso pertinente.
    @Override
    public void calcularResultado(){

        Resultado resultado;
        Operacion operacion;
        String[] operandos = new String[2];
        Operando operando1, operando2;

        vista.getOperandos(operandos);

        if (isOperandosCorrectos(operandos)) {
            operando1 = new Operando(Integer.parseInt(operandos[0]));
            operando2 = new Operando(Integer.parseInt(operandos[1]));
        } else {
            return;
        }

        switch(tipoOperacion){
            case SUMA: operacion = new CasoUsoSuma(operando1, operando2); break;
            case RESTA: operacion = new CasoUsoResta(operando1, operando2); break;
            case MULTIPLICACION: operacion = new CasoUsoMultiplicacion(operando1, operando2); break;
            case DIVISION: operacion = new CasoUsoDivision(operando1, operando2); break;
            default: vista.mostrarError("Aún no se ha indicado el tipo de operación."); return;
        }

        // Ejecuta el caso de uso
        operacion.calcular();
        resultado = operacion.getResultado();

        // Se llama a un método de la vista para devolverle el resultado.
        vista.mostrarResultado(resultado.getValor());
    }

    // Comprueba que se hayan especificado los operandos.
    public boolean isOperandosCorrectos(String[] operandos){

        boolean operandosCorrectos;
        String mensajeError;

        if (operandos[0].isEmpty()){
            mensajeError = "Falta el primer operando";
            operandosCorrectos = false;
        } else if (operandos[1].isEmpty()){
            mensajeError = "Falta el segundo operando";
            operandosCorrectos = false;
        } else {
            mensajeError = "";
            operandosCorrectos = true;
        }

        if (!operandosCorrectos){
            vista.mostrarError(mensajeError);
        }

        return operandosCorrectos;
    }
}
