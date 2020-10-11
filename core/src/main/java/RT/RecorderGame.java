package RT;

import net.spookygames.gdx.nativefilechooser.NativeFileChooser;

public class RecorderGame extends BaseGame {

    NativeFileChooser fileChooser;

    public RecorderGame(NativeFileChooser fileChooser) {
        super();
        this.fileChooser = fileChooser;
    }

    @Override
    public void create() {

        super.create();

        setActiveScreen( new RecorderScreen(fileChooser) );
    }
}
