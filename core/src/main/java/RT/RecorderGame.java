package RT;

public class RecorderGame extends BaseGame {

    @Override
    public void create() {

        super.create();
        setActiveScreen( new RecorderScreen() );
    }
}
