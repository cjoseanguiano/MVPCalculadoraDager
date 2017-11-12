package ejemplo.codictados.mvp.dagger;

import dagger.Module;
import dagger.Provides;
import ejemplo.codictados.mvp.contratos.Contrato;
import ejemplo.codictados.mvp.iu.MainActivity;
import ejemplo.codictados.mvp.presentadores.Presentador;

@Module(injects = {MainActivity.class})
public class MainModule {

    @Provides
    public Contrato.Presentador provideMainActivityPresenter(){
        return new Presentador();
    }
}
