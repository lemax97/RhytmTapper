package RT;

public class RhytmGame extends BaseGame {

    @Override
    public void create() {
        super.create();
        setActiveScreen( new RhytmScreen() );
    }
}
