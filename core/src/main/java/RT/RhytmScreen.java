package RT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.util.ArrayList;
import java.util.Collections;

public class RhytmScreen extends BaseScreen {

    private ArrayList<String> keyList;
    private ArrayList<Color> colorList;
    private ArrayList<TargetBox> targetList;
    private ArrayList<ArrayList<FallingBox>> fallingList;

    @Override
    public void initialize() {

        BaseActor background = new BaseActor(0, 0, mainStage);

    }

    @Override
    public void update(float dt) {

    }
}
