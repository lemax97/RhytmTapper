package RT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.audio.Music;

import java.io.File;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;


public class RecorderScreen extends BaseScreen {

    Music music;
    SongData songData;
    float lastSongPosition;
    boolean recording;
    TextButton loadButton;
    TextButton recordButton;
    TextButton saveButton;
    FileHandle musicFile;
    Skin skin;

    @Override
    public void initialize() {

        recording = false;
        skin = new Skin();
        skin.add("space", new Texture("assets/space.png"));
        loadButton = new TextButton("Load Music File", BaseGame.textButtonStyle);
        loadButton.addListener(
                (Event e) -> {
                    if (!isTouchDownEvent(e))
                        return false;

                    FileChooser files = new FileChooser("Choose music file", skin) {

                        @Override
                        protected void result(Object object) {
                            if (object.equals("OK")) {
                                musicFile = getFile();
                            }
                        }
                    };



//                    FileHandle musicFile = FileUtils.showOpenDialog();

//                    File openFile = SynchronousJFXFileChooser.showOpenDialog();
//                    FileHandle musicFile = new FileHandle(openFile);

                    if ( musicFile != null ) {

                        music = Gdx.audio.newMusic((musicFile));
                        songData = new SongData();
                        songData.setSongName( musicFile.name() );
                    }

                    return true;
                }
        );

        recordButton = new TextButton( "Record Keystrokes", BaseGame.textButtonStyle);
        recordButton.addListener(
                (Event e) -> {
                    if( !isTouchDownEvent(e) )
                        return false;

                    if ( !recording ) {

                        music.play();
                        recording = true;
                        lastSongPosition = 0;
                    }

                    return true;
                }
        );

        saveButton = new TextButton("Save Keystroke File", BaseGame.textButtonStyle );
        saveButton.addListener(
                (Event e) -> {
                    if ( !isTouchDownEvent(e) )
                        return false;

                    FileHandle textFile = FileUtils.showSaveDialog();

                    if ( textFile != null )
                        songData.writeToFile(textFile);

                    return true;
                }
        );

        uiTable.add(loadButton);
        uiTable.row();
        uiTable.add(recordButton);
        uiTable.row();
        uiTable.add(saveButton);
    }

    @Override
    public void update(float dt) {

        if ( recording ) {

            if ( music.isPlaying() )
                lastSongPosition = music.getPosition();
            else {// song just finished

                recording = false;
                songData.setSongDuration( lastSongPosition );
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {

        if ( recording ) {

            String key = Keys.toString(keycode);
            Float time = music.getPosition();
            songData.addKeyTime(key, time);
        }
        return false;
    }
}
