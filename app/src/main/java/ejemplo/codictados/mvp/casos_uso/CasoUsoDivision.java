package ejemplo.codictados.mvp.casos_uso;

import ejemplo.codictados.mvp.modelos.Operando;
import ejemplo.codictados.mvp.modelos.Resultado;

public class CasoUsoDivision implements Operacion {

    private Operando operando1;
    private Operando operando2;
    private Resultado resultado;

    public CasoUsoDivision(Operando operando1, Operando operando2) {
        this.operando1 = operando1;
        this.operando2 = operando2;
    }

    @Override
    public void calcular() {
        int valorResultado;

        // Contemplamos la división entre cero
        if (operando2.getValor() == 0){
            valorResultado = 0;
        } else {
            valorResultado = operando1.getValor() / operando2.getValor();
        }

        resultado = new Resultado(valorResultado);
    }

    @Override
    public Resultado getResultado() {
        return resultado;
    }
}
