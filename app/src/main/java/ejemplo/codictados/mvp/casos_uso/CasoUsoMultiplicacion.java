package ejemplo.codictados.mvp.casos_uso;

import ejemplo.codictados.mvp.modelos.Operando;
import ejemplo.codictados.mvp.modelos.Resultado;

public class CasoUsoMultiplicacion implements Operacion {

    private Operando operando1;
    private Operando operando2;
    private Resultado resultado;

    public CasoUsoMultiplicacion(Operando operando1, Operando operando2) {
        this.operando1 = operando1;
        this.operando2 = operando2;
    }

    @Override
    public void calcular() {
        resultado = new Resultado(operando1.getValor() * operando2.getValor());
    }

    @Override
    public Resultado getResultado() {
        return resultado;
    }
}
